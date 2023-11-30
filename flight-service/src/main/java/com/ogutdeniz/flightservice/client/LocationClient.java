package com.ogutdeniz.flightservice.client;

import com.ogutdeniz.flightservice.dto.LocationApiResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:8088", value = "LOCATION-SERVICE")
public interface LocationClient {
    @GetMapping("api/v1/location/country/{country}/city/{city}")
    LocationApiResponseDto findLocationByCountryAndCity(@PathVariable String country, @PathVariable String city);
}
