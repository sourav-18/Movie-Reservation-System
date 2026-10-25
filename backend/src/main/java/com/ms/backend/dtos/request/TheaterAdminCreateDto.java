package com.ms.backend.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TheaterAdminCreateDto {
    @Email
    @NotBlank
    private String email;

    @Size(max = 100,min = 2)
    @NotBlank
    private String password;
}
