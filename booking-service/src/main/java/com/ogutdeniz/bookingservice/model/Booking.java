package com.ogutdeniz.bookingservice.model;

import com.ogutdeniz.bookingservice.constant.PassengerType;
import com.ogutdeniz.bookingservice.constant.SpecialRequest;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Document(collection = "bookings")
public class Booking {
    @Id
    private UUID id;

    @NotEmpty(message = "User ID cannot be empty")
    private String userId;

    @NotNull(message = "Booking code cannot be null")
    private UUID bookingCode;

    @NotEmpty(message = "Flight code cannot be empty")
    private String flightCode;

    @NotEmpty(message = "Passenger name cannot be empty")
    private String passengerName;

    @Min(value = 1, message = "Number of passengers must be at least 1")
    @Max(value = 9, message = "Number of passengers can't exceed 9")
    private int numberOfPassengers;

    @NotNull(message = "Passenger type cannot be null")
    private PassengerType passengerType;

    @NotEmpty(message = "Special requests cannot be empty")
    private Set<SpecialRequest> specialRequests;

    private BigDecimal price;

    public Booking() {
    }

    public Booking(UUID id, String userId, UUID bookingCode, String flightClientResponse, String passengerName, int numberOfPassengers, PassengerType passengerType, Set<SpecialRequest> specialRequests, BigDecimal price) {
        this.id = id;
        this.userId = userId;
        this.bookingCode = bookingCode;
        this.flightCode = flightClientResponse;
        this.passengerName = passengerName;
        this.numberOfPassengers = numberOfPassengers;
        this.passengerType = passengerType;
        this.specialRequests = specialRequests;
        this.price = price;
    }

    public Booking(String userId, UUID bookingCode, String flightClientResponse, String passengerName, int numberOfPassengers, PassengerType passengerType, Set<SpecialRequest> specialRequests, BigDecimal price) {
        this.userId = userId;
        this.bookingCode = bookingCode;
        this.flightCode = flightClientResponse;
        this.passengerName = passengerName;
        this.numberOfPassengers = numberOfPassengers;
        this.passengerType = passengerType;
        this.specialRequests = specialRequests;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UUID getBookingCode() {
        return bookingCode;
    }

    public void setBookingCode(UUID bookingCode) {
        this.bookingCode = bookingCode;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public PassengerType getPassengerType() {
        return passengerType;
    }

    public void setPassengerType(PassengerType passengerType) {
        this.passengerType = passengerType;
    }

    public Set<SpecialRequest> getSpecialRequests() {
        return specialRequests;
    }

    public void setSpecialRequests(Set<SpecialRequest> specialRequests) {
        this.specialRequests = specialRequests;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
