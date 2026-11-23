package com.ms.backend.dtos.response;


import com.ms.backend.entities.types.TheaterStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TheaterDetailsDto {
    private Long id;
    private String name;
    private TheaterStatus status;
    private CityDto city;
    private AdminDto createdBy;
    private LocalDateTime createdAt;
}
