package com.ogutdeniz.weatherservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class WeatherServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherServiceApplication.class, args);
	}

}
