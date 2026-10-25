package com.ms.backend.dtos.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TheaterAdminDto {
    private Long id;
    private String email;
    @JsonIgnore
    private String token;
}
