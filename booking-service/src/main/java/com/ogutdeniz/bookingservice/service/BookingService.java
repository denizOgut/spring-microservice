package com.ogutdeniz.bookingservice.service;

import com.ogutdeniz.bookingservice.repository.BookingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private static final Logger logger = LoggerFactory.getLogger(BookingService.class);

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }
}
