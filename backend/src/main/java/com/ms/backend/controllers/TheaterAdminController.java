package com.ms.backend.controllers;

import com.ms.backend.dtos.request.ScreenCreateDto;
import com.ms.backend.dtos.request.SeatListCreateDto;
import com.ms.backend.dtos.response.*;
import com.ms.backend.entities.Screen;
import com.ms.backend.services.ScreenService;
import com.ms.backend.services.SeatService;
import com.ms.backend.services.TheaterService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        Long theaterAdminId = (Long) request.getAttribute("auth-id");
        TheaterDto theaterDto = theaterService.getMyTheaterDetails(theaterAdminId);
        ApiResponseDto<TheaterDto> apiResponse =
                new ApiResponseDto<>(200, "Theater details fetch successfully", theaterDto);
        return ResponseEntity.status(200).body(apiResponse);
    }

    @PostMapping("/theater/screen")
    public ResponseEntity<ApiResponseDto<ScreenDto>> screenCreate(HttpServletRequest request, @Valid @RequestBody ScreenCreateDto body) {
        Long theaterAdminId = (Long) request.getAttribute("auth-id");
        ScreenDto screenDto = screenService.createScreen(body, theaterAdminId);
        ApiResponseDto<ScreenDto> apiResponse =
                new ApiResponseDto<>(201, "Screen create successfully", screenDto);
        return ResponseEntity.status(201).body(apiResponse);
    }

    @GetMapping("/theater/screen")
    public ResponseEntity<ApiResponseDto<List<ScreenDto>>> getMyTheaterScreenDetails(
            HttpServletRequest request
            ) {
        Long theaterAdminId = (Long) request.getAttribute("auth-id");
        List<ScreenDto> screens= screenService.getScreenByTheater(theaterAdminId);
        ApiResponseDto<List<ScreenDto>> apiResponse =
                new ApiResponseDto<>(200, "Theater screen fetch successfully",screens);
        return ResponseEntity.status(200).body(apiResponse);
    }

    @PostMapping("/theater/screen/seats")
    public ResponseEntity<ApiResponseDto<SeatListDto>> seatCreate(HttpServletRequest request, @Valid @RequestBody SeatListCreateDto body) {
        Long theaterAdminId = (Long) request.getAttribute("auth-id");
        SeatListDto seatListDto = seatService.create(body, theaterAdminId);
        ApiResponseDto<SeatListDto> apiResponse =
                new ApiResponseDto<>(201, "Seat create successfully", seatListDto);
        return ResponseEntity.status(201).body(apiResponse);
    }

    @GetMapping("/theater/screen/{screenId}/seats")
    public ResponseEntity<ApiResponseDto<List<SeatDto>>> getMyTheaterScreen(
            HttpServletRequest request,
            @PathVariable("screenId") Long screenId
    ) {
        Long theaterAdminId = (Long) request.getAttribute("auth-id");
        List<SeatDto> screens= seatService.getSeatByScreen(theaterAdminId,screenId);
        ApiResponseDto<List<SeatDto>> apiResponse =
                new ApiResponseDto<>(200, "Theater screen seats fetch successfully",screens);
        return ResponseEntity.status(200).body(apiResponse);
    }


}
