package com.ms.backend.controllers;

import com.ms.backend.data.ConstantData;
import com.ms.backend.dtos.request.*;
import com.ms.backend.dtos.response.*;
import com.ms.backend.services.MovieService;
import com.ms.backend.services.TheaterAdminService;
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
    private final MovieService movieService;
    private final TheaterAdminService theaterAdminService;

    @PostMapping("/theaters")
    public ResponseEntity<ApiResponseDto<TheaterDto>> theaterCreate(HttpServletRequest request, @Valid @RequestBody TheaterCreateDto body) {
        Long adminId = (Long) request.getAttribute("auth-id");
        TheaterDto theaterDto = theaterService.create(body, adminId);
        ApiResponseDto<TheaterDto> apiResponse =
                new ApiResponseDto<>(201, "Theater Create successfully", theaterDto);
        return ResponseEntity.status(201).body(apiResponse);
    }

    @GetMapping("/theaters")
    public ResponseEntity<ApiResponseDto<List<TheaterDetailsDto>>> theaterList(
            @RequestParam(defaultValue = ConstantData.PAGINATION_DEFAULT_PAGE_NUMBER + "") Integer page,
            @RequestParam(defaultValue = ConstantData.PAGINATION_DEFAULT_PAGE_SIZE + "") Integer limit
    ) {
        List<TheaterDetailsDto> theaters = theaterService.getTheaters(page, limit);
        ApiResponseDto<List<TheaterDetailsDto>> apiResponse =
                new ApiResponseDto<>(200, "Theater list fetch successfully", theaters);
        return ResponseEntity.status(200).body(apiResponse);
    }

    @PatchMapping("/theaters/status")
    public ResponseEntity<ApiResponseDto<Void>> theaterStatusUpdate(
            @RequestBody TheaterStatusUpdateDto body
    ) {
        theaterService.statusUpdate(body);
        ApiResponseDto<Void> apiResponse =
                new ApiResponseDto<>(200, "Theater status update successfully", null);
        return ResponseEntity.status(200).body(apiResponse);
    }

    @PutMapping("/theaters")
    public ResponseEntity<ApiResponseDto<TheaterDto>> theaterUpdate(
            @Valid @RequestBody TheaterUpdateDto body) {
        TheaterDto theaterDto = theaterService.update(body);
        ApiResponseDto<TheaterDto> apiResponse =
                new ApiResponseDto<>(200, "Theater update successfully", theaterDto);
        return ResponseEntity.status(200).body(apiResponse);
    }

    @PostMapping("/movies")
    public ResponseEntity<ApiResponseDto<MovieDto>> create(
            HttpServletRequest request,
            @Valid @RequestBody MovieCreateDto dto) {
        Long adminId = (Long) request.getAttribute("auth-id");
        MovieDto movieDto = movieService.create(dto, adminId);
        ApiResponseDto<MovieDto> apiResponse =
                new ApiResponseDto<>(201, "movie create successfully", movieDto);
        return ResponseEntity.status(201).body(apiResponse);
    }

    @GetMapping("/movies")
    public ResponseEntity<ApiResponseDto<List<MovieDto>>> movieList(
            @RequestParam(defaultValue = ConstantData.PAGINATION_DEFAULT_PAGE_NUMBER + "") Integer page,
            @RequestParam(defaultValue = ConstantData.PAGINATION_DEFAULT_PAGE_SIZE + "") Integer limit
    ) {
        List<MovieDto> theaters = movieService.listForAdmin(page, limit);
        ApiResponseDto<List<MovieDto>> apiResponse =
                new ApiResponseDto<>(200, "movie list fetch successfully", theaters);
        return ResponseEntity.status(200).body(apiResponse);
    }

    @PatchMapping("/movies/status")
    public ResponseEntity<ApiResponseDto<Void>> movieStatusUpdate(
            @RequestBody MovieStatusUpdateDto body
    ) {
        movieService.statusUpdate(body);
        ApiResponseDto<Void> apiResponse =
                new ApiResponseDto<>(200, "movie status update successfully", null);
        return ResponseEntity.status(200).body(apiResponse);
    }

    @PutMapping("/movies")
    public ResponseEntity<ApiResponseDto<MovieDto>> movieUpdate(
           @Valid @RequestBody MovieUpdateDto body
    ) {
        MovieDto update = movieService.update(body);
        ApiResponseDto<MovieDto> apiResponse =
                new ApiResponseDto<>(200, "movie status update successfully", update);
        return ResponseEntity.status(200).body(apiResponse);
    }

    @GetMapping("/theaters/{theaterId}/theater-admins")
    public ResponseEntity<ApiResponseDto<List<TheaterAdminDto>>> getTheaterAdmins(
            @PathVariable Long theaterId
    ) {
        List<TheaterAdminDto> theaterAdmins = theaterAdminService.listByTheaterId(theaterId);
        ApiResponseDto<List<TheaterAdminDto>> apiResponse =
                new ApiResponseDto<>(200, "Theater admin fetch successfully", theaterAdmins);
        return ResponseEntity.status(200).body(apiResponse);
    }

}
