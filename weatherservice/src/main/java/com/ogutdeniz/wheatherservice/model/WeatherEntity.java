package com.ogutdeniz.wheatherservice.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
@Table(name = "weather_data")
public class WeatherEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @Column(name = "city_name", nullable = false)
    private String cityName;
    private String country;

    private Integer temperature;
    @Column(name = "wind_speed_in_kph")
    private double windSpeedInKph;
    @Column(name = "weather_condition")
    private String weatherCondition;

    private LocalDateTime responseTime;

    private WeatherEntity(String id, String cityName, String country, Integer temperature, double windSpeedInKph, String weatherCondition, LocalDateTime responseTime) {
        this.id = id;
        this.cityName = cityName;
        this.country = country;
        this.temperature = temperature;
        this.windSpeedInKph = windSpeedInKph;
        this.weatherCondition = weatherCondition;
        this.responseTime = responseTime;
    }

    private WeatherEntity(String cityName, String country, Integer temperature, double windSpeedInKph, String weatherCondition, LocalDateTime responseTime) {
        this.id = "";
        this.cityName = cityName;
        this.country = country;
        this.temperature = temperature;
        this.windSpeedInKph = windSpeedInKph;
        this.weatherCondition = weatherCondition;
        this.responseTime = responseTime;
    }

    public WeatherEntity() {

    }


    private String getId() {
        return id;
    }

    private String getCityName() {
        return cityName;
    }

    private String getCountry() {
        return country;
    }

    private Integer getTemperature() {
        return temperature;
    }

    private double getWindSpeedInKph() {
        return windSpeedInKph;
    }

    private String getWeatherCondition() {
        return weatherCondition;
    }

    private LocalDateTime getResponseTime() {
        return responseTime;
    }
}
