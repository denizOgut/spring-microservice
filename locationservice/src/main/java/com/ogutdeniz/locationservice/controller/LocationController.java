package com.ogutdeniz.locationservice.controller;

import com.ogutdeniz.locationservice.dto.APIResponseDto;
import com.ogutdeniz.locationservice.dto.LocationDto;
import com.ogutdeniz.locationservice.model.Location;
import com.ogutdeniz.locationservice.service.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/location")
@Tag(name = "Location Service API v1", description = "Location Service API to find location information's based on geographical data")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @Operation(
            method = "GET",
            summary = "find the location information's based on the country and the city.",
            description = "find the location information's based on the country and the city.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The location information's of the city",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = APIResponseDto.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "City name is wrong. Re-try with a valid country name and city name",
                            content = @Content(schema = @Schema(hidden = true))
                    ),

                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal/External server error.",
                            content = @Content(schema = @Schema(hidden = true))
                    )
            }
    )
    @GetMapping("/country/{country}/city/{city}")
    public ResponseEntity<APIResponseDto> findLocationByCountryAndCity(@PathVariable String country, @PathVariable String city) {
        return ResponseEntity.ok(locationService.findLocationByCountryAndCity(country,city));
    }

    @Operation(
            method = "GET",
            summary = "find the location information's based on longitude and latitude.",
            description = "find the location information's based on longitude and latitude.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The location information's of the longitude and latitude",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = APIResponseDto.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Longitude and latitude are wrong. Re-try with a valid longitude and latitude",
                            content = @Content(schema = @Schema(hidden = true))
                    ),

                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal/External server error.",
                            content = @Content(schema = @Schema(hidden = true))
                    )
            }
    )
    @GetMapping("/longitude/{longitude}/latitude/{latitude}")
    public ResponseEntity<APIResponseDto> findLocationByLongitudeAndLatitude(@PathVariable Double longitude, @PathVariable Double latitude) {
        return ResponseEntity.ok(locationService.findLocationByLongitudeAndLatitude(longitude, latitude));
    }

    @Operation(
            method = "GET",
            summary = "find the location information's based on country.",
            description = "find the location information's based on country.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The location information's of the country",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = APIResponseDto.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "City name is wrong. Re-try with a valid country name",
                            content = @Content(schema = @Schema(hidden = true))
                    ),

                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal/External server error.",
                            content = @Content(schema = @Schema(hidden = true))
                    )
            }
    )
    @GetMapping("/country/{country}")
    public ResponseEntity<List<APIResponseDto>> findAllLocationsByCountry(@PathVariable String country) {
        return ResponseEntity.ok(locationService.findAllLocationsByCountry(country));
    }

    @Operation(
            method = "GET",
            summary = "find the location information's based on continent.",
            description = "find the location information's based on continent.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The location information's of the continent",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = APIResponseDto.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "City name is wrong. Re-try with a valid continent name",
                            content = @Content(schema = @Schema(hidden = true))
                    ),

                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal/External server error.",
                            content = @Content(schema = @Schema(hidden = true))
                    )
            }
    )
    @GetMapping("/continent{continent}")
    public ResponseEntity<List<APIResponseDto>> findAllLocationsByContinent(@PathVariable String continent) {
        return ResponseEntity.ok(locationService.findAllLocationsByCountry(continent));
    }

    @Operation(
            method = "POST",
            summary = "save the location into db.",
            description = "save the location into db.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The location saved into db",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = APIResponseDto.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No location record saved",
                            content = @Content(schema = @Schema(hidden = true))
                    ),

                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal/External server error.",
                            content = @Content(schema = @Schema(hidden = true))
                    )
            }
    )
    @PostMapping("/locations")
    public ResponseEntity<LocationDto> saveLocation(@Valid @RequestBody Location location) {
        return ResponseEntity.ok(locationService.saveLocation(location));
    }

}
