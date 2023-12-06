package com.ogutdeniz.bookingservice.dto;
import com.ogutdeniz.bookingservice.constant.AirlineCompany;


import java.math.BigDecimal;
import java.time.Duration;
import java.time.ZonedDateTime;

public record FlightDto(String flightCode,

                        AirlineCompany airlineCompany,

                        String departureCountry,
                        String departureCity,

                        String arrivalCountry,
                        String arrivalCity,

                        ZonedDateTime departureDateTime,

                        ZonedDateTime arrivalDateTime,
                        Duration flightDuration,
                        BigDecimal price) {

    public static FlightDto convertToFlightDto(Flight from) {
        return new FlightDto(from.flightCode(),
                from.airlineCompany(),
                from.departureCountry(),
                from.departureCity(),
                from.arrivalCountry(),
                from.arrivalCity(),
                from.departureDateTime(),
                from.arrivalDateTime(),
                from.flightDuration(),
                from.price());
    }
}
