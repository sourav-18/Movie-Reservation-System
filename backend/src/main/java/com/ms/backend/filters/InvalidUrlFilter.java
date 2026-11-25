package com.ms.backend.filters;

import com.ms.backend.dtos.response.ApiErrorResponseDto;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class InvalidUrlFilter extends OncePerRequestFilter {

    private final RequestMappingHandlerMapping handlerMapping;
    private final ObjectMapper objectMapper;


    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        try {
            HandlerExecutionChain handler =
                    handlerMapping.getHandler(request);

            if (handler == null) {

                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.setContentType("application/json");
                ApiErrorResponseDto errorResponse=new ApiErrorResponseDto(404,"Invalid URL. Please enter a valid API route.");

                response.getWriter().write(objectMapper.writeValueAsString(errorResponse));

                return;
            }

        } catch (Exception e) {
            throw new ServletException(e);
        }

        filterChain.doFilter(request, response);
    }
}
