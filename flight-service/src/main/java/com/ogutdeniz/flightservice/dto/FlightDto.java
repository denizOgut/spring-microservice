package com.ogutdeniz.flightservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ogutdeniz.flightservice.constant.AirlineCompany;
import com.ogutdeniz.flightservice.model.Flight;
import com.ogutdeniz.flightservice.serializable.CustomZonedDateTimeDeserializer;
import com.ogutdeniz.flightservice.serializable.CustomZonedDateTimeSerializer;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.ZonedDateTime;

public record FlightDto(String flightCode,

                        AirlineCompany airlineCompany,

                        String departureCity,
                        String arrivalCity,
                        @JsonSerialize(using = CustomZonedDateTimeSerializer.class)
                        @JsonDeserialize(using = CustomZonedDateTimeDeserializer.class)
                        ZonedDateTime departureDateTime,
                        @JsonSerialize(using = CustomZonedDateTimeSerializer.class)
                        @JsonDeserialize(using = CustomZonedDateTimeDeserializer.class)
                        ZonedDateTime arrivalDateTime,
                        Duration flightDuration,
                        BigDecimal price) {

    public static FlightDto convertToLocationDto(Flight from) {
        return new FlightDto(from.getFlightCode(),
                from.getAirlineCompany(),
                from.getDepartureCity(),
                from.getArrivalCity(),
                from.getDepartureDateTime(),
                from.getArrivalDateTime(),
                from.getFlightDuration(),
                from.getPrice());
    }
}
