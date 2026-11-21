package com.ms.backend.exceptions;

public class SeatAlreadyExistsException extends RuntimeException {
    public SeatAlreadyExistsException(int rowNumber, int columnNumber) {
        super(String.format(
                "Seat already exists at row %d and column %d",
                rowNumber,
                columnNumber
        ));
    }
}
