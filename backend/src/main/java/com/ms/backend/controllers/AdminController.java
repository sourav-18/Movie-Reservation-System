package com.ms.backend.controllers;

import com.ms.backend.dtos.request.TheaterCreateDto;
import com.ms.backend.dtos.response.ApiResponseDto;
import com.ms.backend.dtos.response.TheaterDetailsDto;
import com.ms.backend.dtos.response.TheaterDto;
import com.ms.backend.services.TheaterService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admins")
@RequiredArgsConstructor
@PreAuthorize("hasRole('admin')")
public class AdminController {

    private final TheaterService theaterService;

    @PostMapping("/theaters")
    public ResponseEntity<ApiResponseDto<TheaterDto>> theaterCreate(HttpServletRequest request, @Valid @RequestBody TheaterCreateDto body) {
        Long adminId = Long.valueOf((String) request.getAttribute("auth-id"));
        TheaterDto theaterDto = theaterService.create(body, adminId);
        ApiResponseDto<TheaterDto> apiResponse =
                new ApiResponseDto<>(201, "Theater Create successfully", theaterDto);
        return ResponseEntity.status(201).body(apiResponse);
    }

    @GetMapping("/theaters")
    public ResponseEntity<ApiResponseDto<List<TheaterDetailsDto>>> theaterList(
            @RequestParam Integer page,
            @RequestParam Integer limit
    ) {
        List<TheaterDetailsDto> theaters = theaterService.getTheaters(page, limit);
        ApiResponseDto<List<TheaterDetailsDto>> apiResponse =
                new ApiResponseDto<>(200, "Theater list fetch successfully", theaters);
        return ResponseEntity.status(200).body(apiResponse);
    }

}
