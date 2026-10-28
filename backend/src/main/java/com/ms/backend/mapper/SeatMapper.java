package com.ms.backend.mapper;

import com.ms.backend.dtos.request.SeatCreateDto;
import com.ms.backend.dtos.response.SeatDto;
import com.ms.backend.dtos.response.SeatListDto;
import com.ms.backend.entities.Screen;
import com.ms.backend.entities.Seat;
import com.ms.backend.entities.Theater;
import com.ms.backend.entities.TheaterAdmin;

import java.util.List;

public class SeatMapper {
    public static Seat toEntity(SeatCreateDto dto, Screen screen, Theater theater, TheaterAdmin theaterAdmin) {
        return Seat.builder()
                .seatType(dto.getSeatType())
                .rowNumber(dto.getRowNumber())
                .columnNumber(dto.getColumnNumber())
                .screen(screen)
                .theater(theater)
                .createdBy(theaterAdmin)
                .build();
    }

    public static SeatDto toDto(Seat entity) {
        return SeatDto.builder().id(entity.getId())
                .seatType(entity.getSeatType())
                .rowNumber(entity.getColumnNumber())
                .columnNumber(entity.getColumnNumber())
                .build();
    }

    public static SeatListDto toDto(List<SeatDto> seats,Screen screen,Theater theater) {
        return SeatListDto.builder()
                .seats(seats)
                .screeId(screen.getId())
                .theaterId(theater.getId())
                .build();
    }

}
