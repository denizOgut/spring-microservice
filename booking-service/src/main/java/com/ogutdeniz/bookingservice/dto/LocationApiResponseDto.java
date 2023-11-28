package com.ogutdeniz.bookingservice.dto;

public record LocationApiResponseDto(LocationDto locationDto,
                                     WeatherDto weatherDto) {
}
