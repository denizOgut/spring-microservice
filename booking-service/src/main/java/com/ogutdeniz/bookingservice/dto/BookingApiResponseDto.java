package com.ogutdeniz.bookingservice.dto;
public record BookingApiResponseDto(BookingDTO bookingDTO,
                                    FlightApiResponseDto flightApiResponseDto) {
}
