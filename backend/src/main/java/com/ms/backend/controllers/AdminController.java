package com.ms.backend.controllers;

import com.ms.backend.dtos.request.TheaterCreateDto;
import com.ms.backend.dtos.response.ApiResponseDto;
import com.ms.backend.dtos.response.TheaterDto;
import com.ms.backend.services.TheaterService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admins")
@RequiredArgsConstructor
public class AdminController {

    private final TheaterService theaterService;

    @PreAuthorize("hasRole('admin')") //todo send custom error message
    @PostMapping("/theaters")
        public ResponseEntity<ApiResponseDto<TheaterDto>> create(HttpServletRequest request, @Valid @RequestBody TheaterCreateDto body) {
        Long adminId = Long.valueOf((String) request.getAttribute("auth-id"));
        TheaterDto theaterDto = theaterService.create(body,adminId);
        ApiResponseDto<TheaterDto> apiResponse =
                new ApiResponseDto<>(201, "Theater Create successfully", theaterDto);
        return ResponseEntity.status(201).body(apiResponse);
    }
}
