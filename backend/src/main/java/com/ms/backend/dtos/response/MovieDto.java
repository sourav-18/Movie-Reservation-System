package com.ms.backend.dtos.response;


import com.ms.backend.entities.Category;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {
    private Long id;
    private String title;
    private String description;
    private String thumbnail;
    private int duration;
    private List<CategoryDto> categories;
}
