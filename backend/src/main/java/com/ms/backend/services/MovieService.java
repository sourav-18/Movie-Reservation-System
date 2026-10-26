package com.ms.backend.services;

import com.ms.backend.dtos.request.MovieCreateDto;
import com.ms.backend.dtos.response.MovieDto;
import com.ms.backend.entities.Category;
import com.ms.backend.entities.Movie;
import com.ms.backend.entities.types.CategoryStatus;
import com.ms.backend.exceptions.DataNotFoundException;
import com.ms.backend.mapper.MovieMapper;
import com.ms.backend.repositories.AdminRepository;
import com.ms.backend.repositories.CategoryRepository;
import com.ms.backend.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final CategoryRepository categoryRepository;
    private final AdminRepository adminRepository;

    public MovieDto create(MovieCreateDto body, Long adminId) {
        List<Category> categories = categoryRepository.findAllById(body.getCategoryIds());
        long count = categories.stream().filter((item) -> item.getStatus() == CategoryStatus.ACTIVE).count();

        if (count != body.getCategoryIds().size()) {
            throw new DataNotFoundException("category id not found");
        }

        Movie newMovie = MovieMapper.toEntity(body, adminRepository.getReferenceById(adminId), categories);
        newMovie.setCategories(categories);
        movieRepository.save(newMovie);

        return MovieMapper.toDto(newMovie);
    }
}
