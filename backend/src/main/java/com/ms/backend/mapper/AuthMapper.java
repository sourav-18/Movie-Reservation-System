package com.ms.backend.mapper;

import com.ms.backend.dtos.request.AdminLoginDto;
import com.ms.backend.dtos.response.AdminDto;
import com.ms.backend.dtos.response.TheaterAdminDto;
import com.ms.backend.entities.Admin;
import com.ms.backend.entities.TheaterAdmin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    AdminDto toDto(Admin dto);
    TheaterAdminDto toDto(TheaterAdmin dto);
}
