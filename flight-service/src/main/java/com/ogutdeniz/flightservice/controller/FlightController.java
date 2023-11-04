package com.ogutdeniz.flightservice.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/flight")
@Tag(name = "Flight Service API v1", description = "Flight Service API to find flight information's based on geographical data")
public class FlightController {
}
