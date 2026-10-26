package com.ms.backend.dtos.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MovieCreateDto {
    @Size(max = 100,min = 2)
    @NotBlank
    private String title;

    @Size(max = 100,min = 20)
    @NotBlank
    private String description;

    @Size(max = 100,min = 5)
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
