package com.ogutdeniz.bookingservice.model;

import com.ogutdeniz.bookingservice.constant.PassengerType;
import com.ogutdeniz.bookingservice.constant.SpecialRequest;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Document(collection = "bookings")
public class Booking {
    @Id
    private String id;

    private String userId;
    private UUID bookingCode;
    private String flightClientResponse;
    private String passengerName;
    private int numberOfPassengers;
    @Field("passengerType")
    @Enumerated(EnumType.STRING)
    private PassengerType passengerType;

    @Field("specialRequests")
    private Set<SpecialRequest> specialRequests;
    private BigDecimal price;

    public Booking() {
    }

    public Booking(String id, String userId, UUID bookingCode, String flightClientResponse, String passengerName, int numberOfPassengers, PassengerType passengerType, Set<SpecialRequest> specialRequests, BigDecimal price) {
        this.id = id;
        this.userId = userId;
        this.bookingCode = bookingCode;
        this.flightClientResponse = flightClientResponse;
        this.passengerName = passengerName;
        this.numberOfPassengers = numberOfPassengers;
        this.passengerType = passengerType;
        this.specialRequests = specialRequests;
        this.price = price;
    }

    public Booking(String userId, UUID bookingCode, String flightClientResponse, String passengerName, int numberOfPassengers, PassengerType passengerType, Set<SpecialRequest> specialRequests, BigDecimal price) {
        this.userId = userId;
        this.bookingCode = bookingCode;
        this.flightClientResponse = flightClientResponse;
        this.passengerName = passengerName;
        this.numberOfPassengers = numberOfPassengers;
        this.passengerType = passengerType;
        this.specialRequests = specialRequests;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getFlightClientResponse() {
        return flightClientResponse;
    }

    public void setFlightClientResponse(String flightClientResponse) {
        this.flightClientResponse = flightClientResponse;
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
