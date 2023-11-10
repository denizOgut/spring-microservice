package com.ogutdeniz.flightservice.controller;

import com.ogutdeniz.flightservice.constant.AirlineCompany;
import com.ogutdeniz.flightservice.dto.FlightApiResponseDto;
import com.ogutdeniz.flightservice.dto.FlightDto;
import com.ogutdeniz.flightservice.model.Flight;
import com.ogutdeniz.flightservice.service.FlightService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;

@RestController
@RequestMapping("api/v1/flight")
@Tag(name = "Flight Service API v1", description = "Flight Service API to find flight information's based on geographical data")
public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/flightCode/{flightCode}")
    public ResponseEntity<FlightApiResponseDto> findFlightByFlightCode(@PathVariable String flightCode) {
        return ResponseEntity.ok(flightService.findFlightByFlightCode(flightCode));
    }

    @GetMapping("/duration/{duration}")
    public ResponseEntity<List<FlightApiResponseDto>> findAllFlightsByFlightDurationGreaterThanEqual(@PathVariable Long duration) {
        return ResponseEntity.ok(flightService.findAllFlightsByFlightDurationGreaterThanEqual(duration));
    }

    @GetMapping("/arrival-country/{arrivalCountry}/arrival-city/{arrivalCity}/airline-company/{airlineCompany}")
    public ResponseEntity<List<FlightApiResponseDto>> findAllFlightsByArrivalCityAndAirlineCompany(@PathVariable String arrivalCountry,
                                                                                                   @PathVariable String arrivalCity,
                                                                                                   @PathVariable AirlineCompany airlineCompany) {
        return ResponseEntity.ok(flightService.findAllFlightsByArrivalCityAndAirlineCompany(arrivalCountry, arrivalCity, airlineCompany));
    }

    @PostMapping("/flights")
    public ResponseEntity<FlightDto> saveFlight(@Valid @RequestBody Flight flight) {
        return ResponseEntity.ok(flightService.saveFlight(flight));
    }
}
