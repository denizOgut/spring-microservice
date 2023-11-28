package com.ogutdeniz.bookingservice.exception;

public class BookingCodeNotFound extends RuntimeException {
    public BookingCodeNotFound(String message) {
        super(message);
    }
}
