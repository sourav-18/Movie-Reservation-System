package com.ms.backend.dtos.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminDto {
    private Long id;
    private String email;
    @JsonIgnore
    private String token;
}
