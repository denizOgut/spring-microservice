package com.ogutdeniz.weatherservice.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
@Table(name = "weather_data")
public class Weather {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "city", nullable = false)
    private String city;
    private String country;

    private double temperature;
    @Column(name = "wind_speed_in_kph")
    private double windSpeedInKph;
    @Column(name = "weather_condition")
    private String weatherCondition;

    private LocalDateTime responseTime;

    public Weather(String id, String city, String country, double temperature, double windSpeedInKph, String weatherCondition) {
        this.id = id;
        this.city = city;
        this.country = country;
        this.temperature = temperature;
        this.windSpeedInKph = windSpeedInKph;
        this.weatherCondition = weatherCondition;
        this.responseTime = LocalDateTime.now();
    }

    public Weather(String city, String country, double temperature, double windSpeedInKph, String weatherCondition) {
        this.id = "";
        this.city = city;
        this.country = country;
        this.temperature = temperature;
        this.windSpeedInKph = windSpeedInKph;
        this.weatherCondition = weatherCondition;
        this.responseTime = LocalDateTime.now();
    }

    public Weather() {
        this.responseTime = LocalDateTime.now();
    }


    public String getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getWindSpeedInKph() {
        return windSpeedInKph;
    }

    public String getWeatherCondition() {
        return weatherCondition;
    }

    public LocalDateTime getResponseTime() {
        return responseTime;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setWindSpeedInKph(double windSpeedInKph) {
        this.windSpeedInKph = windSpeedInKph;
    }

    public void setWeatherCondition(String weatherCondition) {
        this.weatherCondition = weatherCondition;
    }

}
