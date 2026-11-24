package com.ms.backend.services;

import com.ms.backend.dtos.request.MovieCreateDto;
import com.ms.backend.dtos.request.MovieStatusUpdateDto;
import com.ms.backend.dtos.request.MovieUpdateDto;
import com.ms.backend.dtos.request.TheaterStatusUpdateDto;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestHeader;

import java.security.PublicKey;
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

    public List<MovieDto> listForAdmin(int page, int limit) {
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("id").descending());
        Page<Movie> movies = movieRepository.findAll(pageable);
        if (movies.isEmpty()) {
            throw new DataNotFoundException("movie not found");
        }
        return movies.get().map(MovieMapper::toDto).toList();
    }

    @Transactional
    public void statusUpdate(MovieStatusUpdateDto body) {
        Integer isUpdate = movieRepository.statusUpdate(body.getId(), body.getStatus());
        if (isUpdate == 0) {
            throw new DataNotFoundException("Movie not found");
        }
    }

    @Transactional
    public MovieDto update(MovieUpdateDto body){
        List<Category> categories = categoryRepository.findAllById(body.getCategoryIds());
        long count = categories.stream().filter((item) -> item.getStatus() == CategoryStatus.ACTIVE).count();

        if (count != body.getCategoryIds().size()) {
            throw new DataNotFoundException("category id not found");
        }

        Movie movie = movieRepository.findById(body.getId())
                .orElseThrow(() -> new DataNotFoundException("Movie not found"));
        movie.setTitle(body.getTitle());
        movie.setDuration(body.getDuration());
        movie.setThumbnail(body.getThumbnail());
        movie.setDescription(body.getDescription());
        movie.setStatus(body.getStatus());
        movie.setCategories(categories);
        movie.setCreatedBy(adminRepository.getReferenceById(2L));
        return MovieMapper.toDto(movie);
    }


}
