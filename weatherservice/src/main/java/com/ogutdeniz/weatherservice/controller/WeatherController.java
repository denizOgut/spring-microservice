package com.ogutdeniz.weatherservice.controller;

import com.ogutdeniz.weatherservice.dto.WeatherDto;
import com.ogutdeniz.weatherservice.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/api/open-weather")
@Tag(name = "Open Weather Service API v1", description = "Open Weather Service API to search the current weather of the city")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Operation(
            method = "GET",
            summary = "search the current weather report of the city",
            description = "search the current weather of the city.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The current weather report of the city",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = WeatherDto.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "City name is wrong. Re-try with a valid city name",
                            content = @Content(schema = @Schema(hidden = true))
                    ),

                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal/External server error.",
                            content = @Content(schema = @Schema(hidden = true))
                    )
            }
    )
    @GetMapping("/{city}")
    public ResponseEntity<WeatherDto> getWeather(@PathVariable String city) {
        return ResponseEntity.ok(weatherService.getWeather(city));
    }
}
