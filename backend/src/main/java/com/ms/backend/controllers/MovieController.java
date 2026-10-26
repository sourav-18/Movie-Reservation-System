package com.ms.backend.controllers;

import com.ms.backend.dtos.request.MovieCreateDto;
import com.ms.backend.dtos.response.ApiResponseDto;
import com.ms.backend.dtos.response.MovieDto;
import com.ms.backend.services.MovieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PreAuthorize("hasRole('admin')")
    @PostMapping
    public ResponseEntity<ApiResponseDto<MovieDto>> create(HttpServletRequest request, @Valid @RequestBody MovieCreateDto dto) {
        Long adminId = Long.valueOf((String) request.getAttribute("auth-id"));
        MovieDto movieDto = movieService.create(dto, adminId);
        ApiResponseDto<MovieDto> apiResponse =
                new ApiResponseDto<>(201, "movie create successfully", movieDto);
        return ResponseEntity.status(201).body(apiResponse);
    }
}
