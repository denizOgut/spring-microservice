package com.ogutdeniz.locationservice.dto;

public record LocationApiResponseDto(LocationDto locationDto,
                                     WeatherDto weatherDto) {
}
