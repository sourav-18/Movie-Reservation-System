package com.ms.backend.services;

import com.ms.backend.dtos.request.SeatListCreateDto;
import com.ms.backend.dtos.response.SeatDto;
import com.ms.backend.dtos.response.SeatListDto;
import com.ms.backend.entities.Screen;
import com.ms.backend.entities.Seat;
import com.ms.backend.entities.Theater;
import com.ms.backend.entities.TheaterAdmin;
import com.ms.backend.exceptions.DataNotFoundException;
import com.ms.backend.mapper.SeatMapper;
import com.ms.backend.repositories.ScreenRepository;
import com.ms.backend.repositories.SeatRepository;
import com.ms.backend.repositories.TheaterAdminRepository;
import com.ms.backend.repositories.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatService {

    private final TheaterRepository theaterRepository;
    private final ScreenRepository screenRepository;
    private final SeatRepository seatRepository;
    private final TheaterAdminRepository theaterAdminRepository;

    public SeatListDto create(SeatListCreateDto body,Long theaterAdminId) {
        Theater theater = theaterRepository.findById(body.getTheaterId())
                .orElseThrow(() -> new DataNotFoundException("Theater id not found"));

        Screen screen = screenRepository.findById(body.getTheaterId())
                .orElseThrow(() -> new DataNotFoundException("Screen id not found"));

        TheaterAdmin theaterAdminReference = theaterAdminRepository.getReferenceById(theaterAdminId);

        List<Seat> newSeats = body.getSeats().stream().map((item) -> SeatMapper
                .toEntity(item, screen, theater,theaterAdminReference)).toList();
        seatRepository.saveAll(newSeats);

        List<SeatDto> seatDtos = newSeats.stream().map(SeatMapper::toDto).toList();
        return SeatMapper.toDto(seatDtos,screen,theater);
    }

}
