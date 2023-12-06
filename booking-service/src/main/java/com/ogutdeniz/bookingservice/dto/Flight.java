package com.ogutdeniz.bookingservice.dto;

import com.ogutdeniz.bookingservice.constant.AirCraftModel;
import com.ogutdeniz.bookingservice.constant.AirlineCompany;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.ZonedDateTime;

public record Flight(
        long id,

        String flightCode,

        String departureCountry,

        String arrivalCountry,

        String departureCity,


        String arrivalCity,


        ZonedDateTime departureDateTime,


        ZonedDateTime arrivalDateTime,


        Duration flightDuration,


        AirlineCompany airlineCompany,


        AirCraftModel aircraftModel,


        int reservedSeats,


        int availableSeats,


        BigDecimal price
) {
}