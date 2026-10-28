package com.ms.backend.mapper;

import com.ms.backend.dtos.request.TheaterAdminCreateDto;
import com.ms.backend.dtos.request.TheaterCreateDto;
import com.ms.backend.dtos.response.TheaterAdminDto;
import com.ms.backend.entities.TheaterAdmin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TheaterAdminMapper {
    TheaterAdmin toEntity(TheaterAdminCreateDto dto);
    TheaterAdminCreateDto toDto(TheaterCreateDto dto);
    TheaterAdminDto toDto(TheaterAdmin entity);
}
