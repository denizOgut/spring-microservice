package com.ogutdeniz.flightservice.dto;

public record FlightApiResponseDto(FlightDto flightDto, LocationApiResponseDto departureLocationApiResponseDto,LocationApiResponseDto arrivalLocationApiResponseDto) {
}
