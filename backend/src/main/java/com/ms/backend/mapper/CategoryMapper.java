package com.ms.backend.mapper;


import com.ms.backend.dtos.response.CategoryDto;
import com.ms.backend.entities.Category;
import org.mapstruct.Mapper;


public class CategoryMapper {
    public static CategoryDto toDto(Category entity) {
        return CategoryDto.builder().id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
