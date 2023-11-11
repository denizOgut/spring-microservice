package com.ogutdeniz.flightservice.controller;

import com.ogutdeniz.flightservice.constant.AirlineCompany;
import com.ogutdeniz.flightservice.dto.FlightApiResponseDto;
import com.ogutdeniz.flightservice.dto.FlightDto;
import com.ogutdeniz.flightservice.model.Flight;
import com.ogutdeniz.flightservice.service.FlightService;
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


    @Operation(
            method = "GET",
            summary = "find the flight information's based on the flight code.",
            description = "find the flight information's based on the flight code.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The flight information's of the flight code",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = FlightApiResponseDto.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Flight code is wrong. Re-try with a valid flight code",
                            content = @Content(schema = @Schema(hidden = true))
                    ),

                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal/External server error.",
                            content = @Content(schema = @Schema(hidden = true))
                    )
            }
    )
    @GetMapping("/flightCode/{flightCode}")
    public ResponseEntity<FlightApiResponseDto> findFlightByFlightCode(@PathVariable String flightCode) {
        return ResponseEntity.ok(flightService.findFlightByFlightCode(flightCode));
    }


    @Operation(
            method = "GET",
            summary = "find the flight information's based on the duration.",
            description = "find the flight information's based on the duration.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The information's of the flight duration is greater than given duration",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = FlightApiResponseDto.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Duration is wrong. Please try with valid type",
                            content = @Content(schema = @Schema(hidden = true))
                    ),

                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal/External server error.",
                            content = @Content(schema = @Schema(hidden = true))
                    )
            }
    )
    @GetMapping("/duration/{duration}")
    public ResponseEntity<List<FlightApiResponseDto>> findAllFlightsByFlightDurationGreaterThanEqual(@PathVariable Long duration) {
        return ResponseEntity.ok(flightService.findAllFlightsByFlightDurationGreaterThanEqual(duration));
    }

    @Operation(
            method = "GET",
            summary = "find the flight information's based on the arrival location and airline company.",
            description = "find the flight information's based on the arrival location and airline company.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Find the flights to destination with chosen airline company",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = FlightApiResponseDto.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Please input valid parameters",
                            content = @Content(schema = @Schema(hidden = true))
                    ),

                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal/External server error.",
                            content = @Content(schema = @Schema(hidden = true))
                    )
            }
    )
    @GetMapping("/arrival-country/{arrivalCountry}/arrival-city/{arrivalCity}/airline-company/{airlineCompany}")
    public ResponseEntity<List<FlightApiResponseDto>> findAllFlightsByArrivalCountryAndArrivalCityAndAirlineCompany(@PathVariable String arrivalCountry,
                                                                                                   @PathVariable String arrivalCity,
                                                                                                   @PathVariable AirlineCompany airlineCompany) {
        return ResponseEntity.ok(flightService.findAllFlightsByArrivalCountryAndArrivalCityAndAirlineCompany(arrivalCountry, arrivalCity, airlineCompany));
    }

    @PostMapping("/flights")
    public ResponseEntity<FlightDto> saveFlight(@Valid @RequestBody Flight flight) {
        return ResponseEntity.ok(flightService.saveFlight(flight));
    }
}
