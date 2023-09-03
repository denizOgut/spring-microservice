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
    @Column(name = "city", nullable = false)
    private String city;
    private String country;

    private Integer temperature;
    @Column(name = "wind_speed_in_kph")
    private double windSpeedInKph;
    @Column(name = "weather_condition")
    private String weatherCondition;

    private LocalDateTime responseTime;

    public WeatherEntity(String id, String city, String country, Integer temperature, double windSpeedInKph, String weatherCondition) {
        this.id = id;
        this.city = city;
        this.country = country;
        this.temperature = temperature;
        this.windSpeedInKph = windSpeedInKph;
        this.weatherCondition = weatherCondition;
        this.responseTime = LocalDateTime.now();
    }

    public WeatherEntity(String city, String country, Integer temperature, double windSpeedInKph, String weatherCondition) {
        this.id = "";
        this.city = city;
        this.country = country;
        this.temperature = temperature;
        this.windSpeedInKph = windSpeedInKph;
        this.weatherCondition = weatherCondition;
        this.responseTime = LocalDateTime.now();
    }

    public WeatherEntity() {
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

    public Integer getTemperature() {
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

    @Override
    public String toString() {
        return "WeatherEntity{" +
                "id='" + id + '\'' +
                ", cityName='" + city + '\'' +
                ", country='" + country + '\'' +
                ", temperature=" + temperature +
                ", windSpeedInKph=" + windSpeedInKph +
                ", weatherCondition='" + weatherCondition + '\'' +
                ", responseTime=" + responseTime +
                '}';
    }
}
