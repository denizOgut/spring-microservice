package com.ogutdeniz.weatherservice.repository;

import com.ogutdeniz.weatherservice.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeatherRepository extends JpaRepository<Weather, String> {
    Optional<Weather> findFirstByCityOrderByResponseTimeDesc(String city);
}
