package com.ogutdeniz.wheatherservice.repository;

import com.ogutdeniz.wheatherservice.model.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<WeatherEntity,String> {

}
