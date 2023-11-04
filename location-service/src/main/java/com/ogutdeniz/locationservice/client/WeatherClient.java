package com.ogutdeniz.locationservice.client;

import com.ogutdeniz.locationservice.dto.WeatherDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:8086", value = "WEATHER-SERVICE")
public interface WeatherClient {
    @GetMapping("api/v1/open-weather/{city}")
    WeatherDto getWeather(@PathVariable String city);
}
