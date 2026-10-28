package com.ms.backend.dtos.request;

import com.ms.backend.entities.types.SeatTypes;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.List;

@Getter
@Setter
public class SeatListCreateDto {
    @NotNull
    @Positive
    private Long theaterId;

    @NotNull
    @Positive
    private Long screenId;

    @NotEmpty
    private List<SeatCreateDto> seats;
}
