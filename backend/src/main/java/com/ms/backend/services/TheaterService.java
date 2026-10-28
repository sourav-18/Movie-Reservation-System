package com.ms.backend.services;

import com.ms.backend.dtos.request.TheaterAdminCreateDto;
import com.ms.backend.dtos.request.TheaterCreateDto;
import com.ms.backend.dtos.response.TheaterDto;
import com.ms.backend.entities.City;
import com.ms.backend.entities.Theater;
import com.ms.backend.entities.TheaterAdmin;
import com.ms.backend.exceptions.DataNotFoundException;
import com.ms.backend.exceptions.EmailAlreadyExistException;
import com.ms.backend.mapper.TheaterAdminMapper;
import com.ms.backend.mapper.TheaterMapper;
import com.ms.backend.repositories.AdminRepository;
import com.ms.backend.repositories.CityRepository;
import com.ms.backend.repositories.TheaterAdminRepository;
import com.ms.backend.repositories.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TheaterService {

    private final CityRepository cityRepository;
    private final AdminRepository adminRepository;
    private final TheaterRepository theaterRepository;
    private final TheaterAdminRepository theaterAdminRepository;

    private final TheaterAdminService theaterAdminService;

    private final TheaterAdminMapper theaterAdminMapper;

    @Transactional
    public TheaterDto create(TheaterCreateDto body, Long adminId) {
        TheaterAdmin isEmailAlreadyExist = theaterAdminRepository.findByEmail(body.getEmail()).orElse(null);

        if (isEmailAlreadyExist != null) {
            throw new EmailAlreadyExistException("Email already exists");
        }
        City city = cityRepository.findById(body.getCityId())
                .orElseThrow(() -> new DataNotFoundException("City id not found"));

        Theater newTheater = TheaterMapper.toEntity(body, city, adminRepository.getReferenceById(adminId));
        theaterRepository.save(newTheater);
        TheaterAdminCreateDto theaterAdminDto = theaterAdminMapper.toDto(body);
        theaterAdminService.create(theaterAdminDto, newTheater);
        return TheaterMapper.toDto(newTheater);
    }

    public TheaterDto getMyTheaterDetails(Long theaterAdminId){
        TheaterAdmin theaterAdmin = theaterAdminRepository.findById(theaterAdminId)
                .orElseThrow(() -> new DataNotFoundException("Theater admin not found"));
        return TheaterMapper.toDto(theaterAdmin.getTheater());
    }
}
