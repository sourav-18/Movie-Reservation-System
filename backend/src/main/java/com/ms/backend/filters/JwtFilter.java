package com.ms.backend.filters;

import com.ms.backend.dtos.response.ApiErrorResponseDto;
import com.ms.backend.entities.Theater;
import com.ms.backend.entities.types.TheaterStatus;
import com.ms.backend.repositories.TheaterRepository;
import com.ms.backend.services.JwtService;
import com.ms.backend.services.TheaterAdminService;
import com.ms.backend.services.TheaterService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final TheaterRepository theaterRepository;
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        if (authHeader != null && authHeader.startsWith("Bearer")) {
            token = authHeader.substring(7);
        }

        if (token != null && !token.isEmpty() && SecurityContextHolder.getContext().getAuthentication() == null) {
            if (!jwtService.isTokenExpired(token)) {
                Claims claims = jwtService.verifySignatureAdnExtractAllClaims(token);
                Long theaterId = claims.get("theater-id", Long.class);

                if (theaterId != null) {
                    Theater theater = theaterRepository.findById(theaterId).orElse(null);
                    if (theater == null || theater.getStatus() == TheaterStatus.INACTIVE) {
                        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                        response.setContentType("application/json");
                        ApiErrorResponseDto errorResponse = new ApiErrorResponseDto
                                (404, "Theater not found or Theater status is not active");
                        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
                        return;
                    } else {
                        request.setAttribute("theater-id", theaterId);
                    }
                }

                Long authId = claims.get("auth-id", Long.class);
                String role = claims.get("Role", String.class);
                request.setAttribute("auth-id", authId);

                List<SimpleGrantedAuthority> simpleGrantedAuthorities = List.of(new SimpleGrantedAuthority("ROLE_" + role));
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(claims.getSubject(), null, simpleGrantedAuthorities);
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        filterChain.doFilter(request, response);


    }
}
