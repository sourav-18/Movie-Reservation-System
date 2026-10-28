package com.ms.backend.controllers;

import com.ms.backend.dtos.request.ScreenCreateDto;
import com.ms.backend.dtos.request.SeatListCreateDto;
import com.ms.backend.dtos.response.ApiResponseDto;
import com.ms.backend.dtos.response.ScreenDto;
import com.ms.backend.dtos.response.SeatListDto;
import com.ms.backend.dtos.response.TheaterDto;
import com.ms.backend.services.ScreenService;
import com.ms.backend.services.SeatService;
import com.ms.backend.services.TheaterService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/theater-admins")
@RequiredArgsConstructor
@PreAuthorize("hasRole('theater-admin')")
public class TheaterAdminController {

    private final TheaterService theaterService;
    private final ScreenService screenService;
    private final SeatService seatService;

    @GetMapping("/my-theater")
    public ResponseEntity<ApiResponseDto<TheaterDto>> getMyTheaterDetails(HttpServletRequest request) {
        Long theaterAdminId = Long.valueOf((String) request.getAttribute("auth-id"));
        TheaterDto theaterDto = theaterService.getMyTheaterDetails(theaterAdminId);
        ApiResponseDto<TheaterDto> apiResponse =
                new ApiResponseDto<>(200, "Theater details fetch successfully", theaterDto);
        return ResponseEntity.status(200).body(apiResponse);
    }

    @PostMapping("/theater/screen")
    public ResponseEntity<ApiResponseDto<ScreenDto>> screenCreate(HttpServletRequest request, @Valid @RequestBody  ScreenCreateDto body) {
        Long theaterAdminId = Long.valueOf((String) request.getAttribute("auth-id"));
        ScreenDto screenDto = screenService.createScreen(body,theaterAdminId);
        ApiResponseDto<ScreenDto> apiResponse =
                new ApiResponseDto<>(201, "Screen create successfully", screenDto);
        return ResponseEntity.status(201).body(apiResponse);
    }

    @PostMapping("/theater/screen/seats")
    public ResponseEntity<ApiResponseDto<SeatListDto>> seatCreate(HttpServletRequest request, @Valid @RequestBody SeatListCreateDto body) {
        Long theaterAdminId = Long.valueOf((String) request.getAttribute("auth-id"));
        SeatListDto seatListDto = seatService.create(body,theaterAdminId);
        ApiResponseDto<SeatListDto> apiResponse =
                new ApiResponseDto<>(201, "Seat create successfully", seatListDto);
        return ResponseEntity.status(201).body(apiResponse);
    }


}
