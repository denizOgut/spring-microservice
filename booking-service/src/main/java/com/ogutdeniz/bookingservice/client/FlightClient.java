package com.ogutdeniz.bookingservice.client;


import com.ogutdeniz.bookingservice.dto.FlightApiResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:8084", value = "FLIGHT-SERVICE")
public interface FlightClient {
    @GetMapping("api/v1/flight/{flightCode}")
    ResponseEntity<FlightApiResponseDto> findFlightByFlightCode(@PathVariable String flightCode);


}
