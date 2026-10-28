package com.ms.backend.dtos.response;

import com.ms.backend.entities.types.SeatTypes;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatDto {
    private long id;
    private SeatTypes seatType;
    private int rowNumber;
    private int columnNumber;
}
