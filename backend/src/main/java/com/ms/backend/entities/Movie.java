package com.ms.backend.entities;

import com.ms.backend.entities.types.MovieStatus;
import com.ms.backend.entities.types.TheaterStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movies",indexes = {
        @Index(name = "ind_movies_status",columnList = "Status")
})
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String thumbnail;

    @Column(nullable = false)
    private int duration; //in minute

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MovieStatus status = MovieStatus.ACTIVE;

    @ManyToMany
    @JoinTable(name = "movie_categories",
            joinColumns = @JoinColumn(
                    name = "movie_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "category_id",
                    referencedColumnName = "id"
            )
    )
    private List<Category> categories;

    @ManyToOne
    @JoinColumn(name = "crated_by",updatable = false)
    private Admin createdBy;
}
