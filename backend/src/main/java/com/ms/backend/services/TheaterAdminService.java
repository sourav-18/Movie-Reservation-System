package com.ms.backend.services;

import com.ms.backend.dtos.request.TheaterAdminCreateDto;
import com.ms.backend.dtos.response.TheaterAdminDto;
import com.ms.backend.entities.TheaterAdmin;
import com.ms.backend.exceptions.EmailAlreadyExistException;
import com.ms.backend.mapper.TheaterAdminMapper;
import com.ms.backend.repositories.TheaterAdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TheaterAdminService {

    private final TheaterAdminRepository theaterAdminRepository;
    private final TheaterAdminMapper theaterAdminMapper;

    public TheaterAdminDto create(TheaterAdminCreateDto body) {
        TheaterAdmin entity = theaterAdminMapper.toEntity(body);
        TheaterAdmin isEmailAlreadyExist = theaterAdminRepository.findByEmail(body.getEmail()).orElse(null);

        if (isEmailAlreadyExist != null) {
            throw new EmailAlreadyExistException("Email already exists");
        }

        theaterAdminRepository.save(entity);
        return theaterAdminMapper.toDto(entity);
    }
}
