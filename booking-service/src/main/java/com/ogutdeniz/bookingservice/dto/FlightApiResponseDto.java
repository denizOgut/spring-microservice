package com.ogutdeniz.bookingservice.dto;
public record FlightApiResponseDto(FlightDto flightDto,
                                   com.ogutdeniz.flightservice.dto.LocationApiResponseDto departureLocationApiResponseDto,
                                   LocationApiResponseDto arrivalLocationApiResponseDto) {

}
