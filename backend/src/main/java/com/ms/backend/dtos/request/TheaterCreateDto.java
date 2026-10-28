package com.ms.backend.dtos.request;

import com.ms.backend.entities.Admin;
import com.ms.backend.entities.City;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
public class TheaterCreateDto {
    @Size(max = 100,min = 2)
    @NotBlank
    private String name;

    @NotNull
    @Positive
    private Long cityId;

    @Email
    @NotBlank
    private String email;

    @Size(max = 100,min = 2)
    @NotBlank
    private String password;

}
