package com.ms.backend.mapper;

import com.ms.backend.dtos.request.TheaterCreateDto;
import com.ms.backend.dtos.response.TheaterDto;
import com.ms.backend.entities.Admin;
import com.ms.backend.entities.City;
import com.ms.backend.entities.Theater;

public class TheaterMapper {
    public  static Theater toEntity(TheaterCreateDto dto, City city, Admin admin) {
        return Theater.builder().name(dto.getName())
                .city(city)
                .createdBy(admin)
                .build();
    }
    public static TheaterDto toDto(Theater entity){
        return TheaterDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .city(CityMapper.toDto(entity.getCity()))
                .createdBy(AdminMapper.toDto(entity.getCreatedBy()))
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
