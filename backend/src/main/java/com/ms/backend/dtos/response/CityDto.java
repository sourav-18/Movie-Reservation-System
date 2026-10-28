package com.ms.backend.dtos.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CityDto {
    private Long id;
    private String name;
}
