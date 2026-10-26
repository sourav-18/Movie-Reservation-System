package com.ms.backend.mapper;

import com.ms.backend.dtos.request.MovieCreateDto;
import com.ms.backend.dtos.response.CategoryDto;
import com.ms.backend.dtos.response.MovieDto;
import com.ms.backend.entities.Admin;
import com.ms.backend.entities.Category;
import com.ms.backend.entities.Movie;
import org.mapstruct.Mapper;

import java.util.List;


public class MovieMapper {
    public static Movie toEntity(MovieCreateDto dto, Admin admin, List<Category> categories) {
        return Movie.builder().title(dto.getTitle())
                .description(dto.getDescription())
                .duration(dto.getDuration())
                .thumbnail(dto.getThumbnail())
                .categories(categories)
                .createdBy(admin)
                .build();
    }

    public static MovieDto toDto(Movie entity) {
        List<CategoryDto> categoryDto = entity.getCategories().stream().map(CategoryMapper::toDto).toList();
        return MovieDto.builder().id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .duration(entity.getDuration())
                .thumbnail(entity.getThumbnail())
                .categories(categoryDto)
                .build();
    }
}
