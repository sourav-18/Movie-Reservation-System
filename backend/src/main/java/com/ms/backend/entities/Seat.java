package com.ms.backend.entities;

import com.ms.backend.entities.types.SeatTypes;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "seats")
@Getter
@Setter
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,name = "seat_type")
    private SeatTypes seatType;

    @Column(nullable = false,name = "row_number")
    private int rowNumber;

    @Column(nullable = false,name = "column_number")
    private int columnNumber;

    @ManyToOne
    @JoinColumn(name = "theater_id",nullable = false)
    private Theater theater;

    @ManyToOne
    @JoinColumn(name = "screen_id",nullable = false)
    private Screen screen;

}
