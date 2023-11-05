package com.ogutdeniz.flightservice.service;

import com.ogutdeniz.flightservice.client.LocationClient;
import com.ogutdeniz.flightservice.repository.FlightRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FlightService {
    private final FlightRepository flightRepository;
    private final LocationClient locationClient;

    private static final Logger logger = LoggerFactory.getLogger(FlightService.class);

    public FlightService(FlightRepository flightRepository, LocationClient locationClient) {
        this.flightRepository = flightRepository;
        this.locationClient = locationClient;
    }
}
