package com.ms.backend.dtos.request;


import com.ms.backend.entities.types.SeatTypes;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SeatCreateDto {

    @NotNull
    private SeatTypes seatType;

    @NotNull
    @Positive
    private int rowNumber;

    @NotNull
    @Positive
    private int columnNumber;
}
