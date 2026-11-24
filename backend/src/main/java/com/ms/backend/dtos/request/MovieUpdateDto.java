package com.ms.backend.dtos.request;

import com.ms.backend.entities.types.MovieStatus;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MovieUpdateDto {

    @NotNull
    @Min(1)
    @Positive
    private Long id;

    @Size(min = 2,max = 100)
    @NotBlank
    private String title;

    @Size(min = 20,max = 5000)
    @NotBlank
    private String description;

    @Size(min = 5,max = 500)
    @NotBlank
    private String thumbnail;

    @Min(2)
    @Max(1000)
    private int duration;

    @NotNull
    private MovieStatus status;

    @NotEmpty
    private List<
            @NotNull
            @Positive
                    Long> categoryIds;
}
