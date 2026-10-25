package com.ms.backend.controllers;

import com.ms.backend.dtos.request.TheaterAdminCreateDto;
import com.ms.backend.dtos.response.TheaterAdminDto;
import com.ms.backend.services.TheaterAdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/theater-admins")
@RequiredArgsConstructor
public class TheaterAdminController {

    private final TheaterAdminService theaterAdminService;

    @PostMapping
    public TheaterAdminDto create(@Valid @RequestBody TheaterAdminCreateDto body){
        TheaterAdminDto theaterAdminDto = theaterAdminService.create(body);
        return theaterAdminDto;
    }
}
