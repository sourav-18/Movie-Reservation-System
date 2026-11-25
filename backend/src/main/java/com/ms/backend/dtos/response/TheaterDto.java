package com.ms.backend.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ms.backend.entities.Admin;
import com.ms.backend.entities.City;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TheaterDto {
    private Long id;
    private String name;
    private CityDto city;
    private AdminDto createdBy;
    private LocalDateTime createdAt;
}
