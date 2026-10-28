package com.ms.backend.services;

import com.ms.backend.dtos.request.TheaterAdminCreateDto;
import com.ms.backend.dtos.response.TheaterAdminDto;
import com.ms.backend.entities.Theater;
import com.ms.backend.entities.TheaterAdmin;
import com.ms.backend.exceptions.EmailAlreadyExistException;
import com.ms.backend.mapper.TheaterAdminMapper;
import com.ms.backend.repositories.TheaterAdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TheaterAdminService {

    private final TheaterAdminRepository theaterAdminRepository;
    private final TheaterAdminMapper theaterAdminMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public TheaterAdminDto create(TheaterAdminCreateDto body, Theater theater) {
        TheaterAdmin entity = theaterAdminMapper.toEntity(body);
        entity.setPassword(passwordEncoder.encode(body.getPassword()));
        entity.setTheater(theater);
        theaterAdminRepository.save(entity);
        return theaterAdminMapper.toDto(entity);
    }
}
