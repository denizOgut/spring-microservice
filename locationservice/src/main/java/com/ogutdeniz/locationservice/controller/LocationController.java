package com.ogutdeniz.locationservice.controller;

import com.ogutdeniz.locationservice.dto.APIResponseDto;
import com.ogutdeniz.locationservice.dto.LocationDto;
import com.ogutdeniz.locationservice.model.Location;
import com.ogutdeniz.locationservice.service.LocationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/location-service")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/{city}")
    public ResponseEntity<APIResponseDto> findLocationByCity(@PathVariable String city) {
        return ResponseEntity.ok(locationService.findLocationByCity(city));
    }

    @GetMapping("/{longitude}/{latitude}")
    public ResponseEntity<APIResponseDto> findLocationByLongitudeAndLatitude(@PathVariable Double longitude, @PathVariable Double latitude) {
        return ResponseEntity.ok(locationService.findLocationByLongitudeAndLatitude(longitude, latitude));
    }

    @GetMapping("{country}")
    public ResponseEntity<List<APIResponseDto>> findAllLocationsByCountry(@PathVariable String country) {
        return ResponseEntity.ok(locationService.findAllLocationsByCountry(country));
    }

    @GetMapping("{continent}")
    public ResponseEntity<List<APIResponseDto>> findAllLocationsByContinent(@PathVariable String continent) {
        return ResponseEntity.ok(locationService.findAllLocationsByCountry(continent));
    }

    @PostMapping("/locations")
    public ResponseEntity<LocationDto> saveLocation(@Valid @RequestBody Location location) {
        return ResponseEntity.ok(locationService.saveLocation(location));
    }

}
