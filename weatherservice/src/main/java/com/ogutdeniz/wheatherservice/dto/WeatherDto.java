package com.ogutdeniz.wheatherservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ogutdeniz.wheatherservice.model.WeatherEntity;

import java.time.LocalDateTime;


public record WeatherDto(String city,
                         String country,
                         Integer temperature,
                         double windSpeedInKph,
                         String weatherCondition,
                         @JsonFormat(pattern = "dd/MM/yyyy kk:mm:ss") LocalDateTime responseTime) {
    public static WeatherDto convertToWeatherDto(WeatherEntity from) {
        return new WeatherDto(
                from.getCity(),
                from.getCountry(),
                from.getTemperature(),
                from.getWindSpeedInKph(),
                from.getWeatherCondition(),
                from.getResponseTime());
    }
}
