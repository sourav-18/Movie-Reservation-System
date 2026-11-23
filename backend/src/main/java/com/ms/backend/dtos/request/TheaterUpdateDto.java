package com.ms.backend.dtos.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TheaterUpdateDto {
    @NotNull
    @Min(1)
    @Positive
    private Long id;

    @Size(max = 100,min = 2)
    @NotBlank
    private String name;

    @NotNull
    @Positive
    private Long cityId;
}
