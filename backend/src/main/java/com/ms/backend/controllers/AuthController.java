package com.ms.backend.controllers;

import com.ms.backend.dtos.request.AdminLoginDto;
import com.ms.backend.dtos.request.TheaterAdminLoginDto;
import com.ms.backend.dtos.response.AdminDto;
import com.ms.backend.dtos.response.ApiResponseDto;
import com.ms.backend.dtos.response.TheaterAdminDto;
import com.ms.backend.services.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/admin/login")
    public ResponseEntity<ApiResponseDto<AdminDto>> adminLogin(@Valid @RequestBody AdminLoginDto body, HttpServletResponse response) {
        AdminDto adminDto = authService.adminLogin(body);
        response.setHeader("X-Access-Token", adminDto.getToken());
        ApiResponseDto<AdminDto> apiResponse =
                new ApiResponseDto<>(200, "Theater Admin Login successfully", adminDto);
        return ResponseEntity.status(200).body(apiResponse);
    }

    @PostMapping("/theater-admin/login")
    public ResponseEntity<ApiResponseDto<TheaterAdminDto>> theaterAdminLogin(
            @Valid @RequestBody TheaterAdminLoginDto body, HttpServletResponse response) {
        TheaterAdminDto theaterAdminDto = authService.theaterAdminLogin(body);
        response.setHeader("X-Access-Token", theaterAdminDto.getToken());
        ApiResponseDto<TheaterAdminDto> apiResponse =
                new ApiResponseDto<>(200, "Theater Admin Login successfully", theaterAdminDto);
        return ResponseEntity.status(200).body(apiResponse);
    }
}
