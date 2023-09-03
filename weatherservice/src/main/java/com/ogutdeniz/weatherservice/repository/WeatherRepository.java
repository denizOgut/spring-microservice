package com.ogutdeniz.weatherservice.repository;

import com.ogutdeniz.weatherservice.model.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<WeatherEntity, String> {
    boolean existsByCity(String city);

    WeatherEntity findByCity(String city);
}
