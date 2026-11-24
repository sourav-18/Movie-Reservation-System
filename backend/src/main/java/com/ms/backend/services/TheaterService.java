package com.ms.backend.services;

import com.ms.backend.dtos.request.TheaterAdminCreateDto;
import com.ms.backend.dtos.request.TheaterCreateDto;
import com.ms.backend.dtos.request.TheaterStatusUpdateDto;
import com.ms.backend.dtos.request.TheaterUpdateDto;
import com.ms.backend.dtos.response.TheaterDetailsDto;
import com.ms.backend.dtos.response.TheaterDto;
import com.ms.backend.entities.City;
import com.ms.backend.entities.Theater;
import com.ms.backend.entities.TheaterAdmin;
import com.ms.backend.entities.types.TheaterStatus;
import com.ms.backend.exceptions.DataNotFoundException;
import com.ms.backend.exceptions.EmailAlreadyExistException;
import com.ms.backend.mapper.TheaterAdminMapper;
import com.ms.backend.mapper.TheaterMapper;
import com.ms.backend.repositories.AdminRepository;
import com.ms.backend.repositories.CityRepository;
import com.ms.backend.repositories.TheaterAdminRepository;
import com.ms.backend.repositories.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

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
        //todo sent password in main
        return TheaterMapper.toDto(newTheater);
    }

    public TheaterDto getMyTheaterDetails(Long theaterAdminId) {
        TheaterAdmin theaterAdmin = theaterAdminRepository.findById(theaterAdminId)
                .orElseThrow(() -> new DataNotFoundException("Theater admin not found"));
        return TheaterMapper.toDto(theaterAdmin.getTheater());
    }

    public List<TheaterDetailsDto> getTheaters(int page, int limit) {
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("id").descending());
        Page<Theater> theaters = theaterRepository.findAll(pageable);
        if (theaters.isEmpty()) {
            throw new DataNotFoundException("Theater not found");
        }
        String ans = "dddf";
        char[] charArray = ans.toCharArray();
        Arrays.sort(charArray);
        return theaters.get().map(TheaterMapper::toDetailsDto).toList();
    }

    @Transactional
    public void statusUpdate(TheaterStatusUpdateDto body) {
        Integer isUpdate = theaterRepository.statusUpdate(body.getId(), body.getStatus());
        if (isUpdate == 0) {
            throw new DataNotFoundException("Theater not found");
        }
    }

    @Transactional
    public TheaterDto update(TheaterUpdateDto body) {
        Theater theater = theaterRepository.findById(body.getId())
                .orElseThrow(() -> new DataNotFoundException("Theater not found"));

        City city = cityRepository.findById(body.getCityId())
                .orElseThrow(() -> new DataNotFoundException("City id not found"));

        theater.setName(body.getName());
        theater.setCity(city);
        return TheaterMapper.toDto(theater);
    }






}
