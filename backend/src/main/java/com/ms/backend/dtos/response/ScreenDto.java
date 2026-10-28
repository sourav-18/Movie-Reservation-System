package com.ms.backend.dtos.response;


import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScreenDto {
    private Long id;
    private Long theaterId;
    private String name;
    private List<SeatDto> seats;
}
