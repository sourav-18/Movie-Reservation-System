package com.ms.backend.services;

import com.ms.backend.dtos.request.ScreenCreateDto;
import com.ms.backend.dtos.response.ScreenDto;
import com.ms.backend.entities.Screen;
import com.ms.backend.entities.Theater;
import com.ms.backend.entities.TheaterAdmin;
import com.ms.backend.exceptions.DataNotFoundException;
import com.ms.backend.mapper.ScreenMapper;
import com.ms.backend.repositories.ScreenRepository;
import com.ms.backend.repositories.TheaterAdminRepository;
import com.ms.backend.repositories.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScreenService {

    private final TheaterRepository theaterRepository;
    private final ScreenRepository screenRepository;
    private final TheaterAdminRepository theaterAdminRepository;


    public ScreenDto createScreen(ScreenCreateDto body, Long theaterAdminId) {
        Theater theater = theaterRepository.findById(body.getTheaterId())
                .orElseThrow(() -> new DataNotFoundException("Theater id not found"));
        TheaterAdmin theaterAdminReference = theaterAdminRepository.getReferenceById(theaterAdminId);
        Screen newScreen = ScreenMapper.toEntity(body, theater, theaterAdminReference);
        screenRepository.save(newScreen);

        return ScreenMapper.toDto(newScreen);
    }
}
