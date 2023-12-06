package com.ogutdeniz.bookingservice.dto;
public record FlightApiResponseDto(FlightDto flightDto,
                                   LocationApiResponseDto departureLocationApiResponseDto,
                                   LocationApiResponseDto arrivalLocationApiResponseDto) {

}
