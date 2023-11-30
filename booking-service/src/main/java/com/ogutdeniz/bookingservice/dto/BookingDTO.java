package com.ogutdeniz.bookingservice.dto;

import com.ogutdeniz.bookingservice.constant.PassengerType;
import com.ogutdeniz.bookingservice.constant.SpecialRequest;
import com.ogutdeniz.bookingservice.model.Booking;


import java.util.Set;
import java.util.UUID;

public record BookingDTO(String id,
                         String userId,
                         UUID bookingCode,
                         String flightClientResponse,
                         String passengerName,
                         int numberOfPassengers,
                         PassengerType passengerType,
                         Set<SpecialRequest> specialRequests) {

    public static BookingDTO convertToBookingDto(Booking from) {
        return new BookingDTO(from.getId(),
                from.getUserId(),
                from.getBookingCode(),
                from.getFlightCode(),
                from.getPassengerName(),
                from.getNumberOfPassengers(),
                from.getPassengerType(),
                from.getSpecialRequests());
    }
}
