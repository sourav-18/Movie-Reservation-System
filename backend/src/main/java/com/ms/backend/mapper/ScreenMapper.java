package com.ms.backend.mapper;

import com.ms.backend.dtos.request.ScreenCreateDto;
import com.ms.backend.dtos.response.ScreenDto;
import com.ms.backend.entities.Screen;
import com.ms.backend.entities.Theater;
import com.ms.backend.entities.TheaterAdmin;

import java.util.ArrayList;

public class ScreenMapper {
    public static Screen toEntity(ScreenCreateDto dto, Theater theater, TheaterAdmin theaterAdmin) {
        return Screen.builder()
                .name(dto.getName())
                .theater(theater)
                .createdBy(theaterAdmin)
                .seats(new ArrayList<>())
                .build();
    }

    public static ScreenDto toDto(Screen entity) {
        return ScreenDto.builder()
                .id(entity.getId())
                .theaterId(entity.getTheater().getId())
                .name(entity.getName())
                .seats(entity.getSeats().stream().map(SeatMapper::toDto).toList())
                .build();

    }
}
