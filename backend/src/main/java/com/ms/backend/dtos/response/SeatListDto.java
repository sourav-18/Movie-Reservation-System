package com.ms.backend.dtos.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatListDto {
    private Long theaterId;
    private Long screeId;
    private Long cratedBy;
    private List<SeatDto> seats;
}
