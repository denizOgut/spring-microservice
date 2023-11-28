package com.ogutdeniz.bookingservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;


public record WeatherDto(String city,
                         String country,
                         double temperature,
                         double windSpeedInKph,
                         String weatherCondition,
                         @JsonFormat(pattern = "dd/MM/yyyy kk:mm:ss") LocalDateTime responseTime) {

}
