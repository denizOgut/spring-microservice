package com.ogutdeniz.bookingservice.controller;

import com.ogutdeniz.bookingservice.service.BookingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/booking")
@Tag(name = "Flight Service API v1", description = "Booking Service API to book a flight")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
}
