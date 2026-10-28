package com.ms.backend.mapper;

import com.ms.backend.dtos.response.CityDto;
import com.ms.backend.entities.City;

public class CityMapper {
    public static CityDto toDto(City city){
        return CityDto.builder()
                .id(city.getId())
                .name(city.getName())
                .build();
    }
}
