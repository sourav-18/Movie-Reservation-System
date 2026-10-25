package com.ms.backend.entities;

import com.ms.backend.entities.types.TheaterAdminStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "theater_admins")
@Getter
@Setter
@ToString
public class TheaterAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TheaterAdminStatus status=TheaterAdminStatus.ACTIVE;
}
