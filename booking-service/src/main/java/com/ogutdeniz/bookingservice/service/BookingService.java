package com.ogutdeniz.bookingservice.service;

import com.ogutdeniz.bookingservice.client.FlightClient;
import com.ogutdeniz.bookingservice.dto.BookingApiResponseDto;
import com.ogutdeniz.bookingservice.dto.BookingDTO;
import com.ogutdeniz.bookingservice.exception.BookingCodeNotFound;
import com.ogutdeniz.bookingservice.repository.BookingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final FlightClient flightClient;
    private static final Logger logger = LoggerFactory.getLogger(BookingService.class);

    public BookingService(BookingRepository bookingRepository, FlightClient flightClient) {
        this.bookingRepository = bookingRepository;
        this.flightClient = flightClient;
    }

    public BookingApiResponseDto findByBookingCode(UUID bookingCode) {
        var booking = bookingRepository.findByBookingCode(bookingCode)
                                       .orElseThrow(() -> new BookingCodeNotFound("Booking not found for given booking code" + bookingCode));
        var flight = flightClient.findFlightByFlightCode(booking.getFlightCode());
        var bookingDto = BookingDTO.convertToBookingDto(booking);

        return new BookingApiResponseDto(bookingDto,flight);
    }
}
