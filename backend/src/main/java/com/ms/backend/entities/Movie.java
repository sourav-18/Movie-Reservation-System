package com.ms.backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "movies")
@Getter
@Setter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String thumbnail;

    @Column(nullable = false)
    private int duration; //in minute

    @ManyToMany
    @JoinTable(name = "movie_categories")
    private List<Category> category;

    @ManyToOne
    @JoinColumn(name = "crated_by")
    private Admin createdBy;
}
