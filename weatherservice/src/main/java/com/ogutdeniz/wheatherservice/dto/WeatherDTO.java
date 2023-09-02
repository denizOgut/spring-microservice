package com.ogutdeniz.wheatherservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record WeatherDTO(String cityName, String country, Integer temperature, double windSpeedInKph,
                         double weatherCondition, @JsonFormat(pattern = "dd/MM/yyyy kk:mm:ss") LocalDateTime responseTime) {
}
