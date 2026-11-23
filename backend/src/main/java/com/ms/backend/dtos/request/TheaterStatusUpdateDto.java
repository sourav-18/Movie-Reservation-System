package com.ms.backend.dtos.request;

import com.ms.backend.entities.types.TheaterStatus;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TheaterStatusUpdateDto {
    @NotNull
    @Min(1)
    @Positive
    private Long id;

    @NotNull
    private TheaterStatus status;
}
