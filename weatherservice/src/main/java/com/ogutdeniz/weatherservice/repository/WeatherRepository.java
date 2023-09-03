package com.ogutdeniz.weatherservice.repository;

import com.ogutdeniz.weatherservice.model.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeatherRepository extends JpaRepository<WeatherEntity, String> {
    Optional<WeatherEntity> findFirstByCityOrderByResponseTimeDesc(String city);
}
