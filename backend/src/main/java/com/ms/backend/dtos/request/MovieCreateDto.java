package com.ms.backend.dtos.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MovieCreateDto {
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

    @NotEmpty
    private List<
            @NotNull
            @Positive
            Long> categoryIds;
}
