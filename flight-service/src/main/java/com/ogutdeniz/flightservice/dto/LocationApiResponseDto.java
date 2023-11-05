package com.ogutdeniz.flightservice.dto;

public record LocationApiResponseDto(LocationDto locationDto,
                                     WeatherDto weatherDto) {
}
