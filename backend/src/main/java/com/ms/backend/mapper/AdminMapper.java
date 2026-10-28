package com.ms.backend.mapper;

import com.ms.backend.dtos.response.AdminDto;
import com.ms.backend.dtos.response.CityDto;
import com.ms.backend.entities.Admin;
import com.ms.backend.entities.City;

public class AdminMapper {
    public static AdminDto toDto(Admin admin){
        return AdminDto.builder()
                .id(admin.getId())
                .email(admin.getEmail())
                .build();
    }
}
