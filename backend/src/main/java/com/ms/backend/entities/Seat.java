package com.ms.backend.entities;

import com.ms.backend.entities.types.SeatStatus;
import com.ms.backend.entities.types.SeatTypes;
import com.ms.backend.entities.types.ShowTimeStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "seats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeatStatus status;

    @ManyToOne
    @JoinColumn(name = "crated_by",nullable = false)
    private TheaterAdmin createdBy;

}
