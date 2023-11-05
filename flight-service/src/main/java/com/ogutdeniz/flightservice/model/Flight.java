package com.ogutdeniz.flightservice.model;

import com.ogutdeniz.flightservice.constant.AirCraftModel;
import com.ogutdeniz.flightservice.constant.AirlineCompany;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "flight_code",unique = true)
    @NotBlank(message = "Flight Code is mandatory")
    private String flightCode;
    @Column(name = "departure_city")
    @NotBlank(message = "Departure City is mandatory")
    private String departureCity;
    @Column(name = "arrival_city")
    @NotBlank(message = "Arrival City is mandatory")
    private String arrivalCity;
    @Column(name = "departure_date_time")
    @FutureOrPresent(message = "Departure DateTime Cant Be In Past")
    private ZonedDateTime departureDateTime;
    @Column(name = "arrival_date_time")
    @FutureOrPresent(message = "Departure DateTime Cant Be In Past")
    private ZonedDateTime arrivalDateTime;
    @Column(name = "flight_duration")
    private Duration flightDuration;
    @Column(name = "airline_company")
    @Enumerated(EnumType.STRING)
    private AirlineCompany airlineCompany;
    @Column(name = "aircraft_model")
    @Enumerated(EnumType.STRING)
    private AirCraftModel aircraftModel;
    @Column(name = "reserved_seats")
    private int reservedSeats;
    @Column(name = "available_seats")
    private int availableSeats;
    @Positive(message = "Price Must Be More Than 0")
    private BigDecimal price;

    public Flight() {
    }

    public Flight(long id, String flightCode,AirlineCompany airlineCompany, String departureCity, String arrivalCity, ZonedDateTime departureDateTime, ZonedDateTime arrivalDateTime, int reservedSeats, BigDecimal price) {
        this.id = id;
        this.flightCode = flightCode;
        this.airlineCompany = airlineCompany;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.reservedSeats = reservedSeats;
        this.price = price;
        this.aircraftModel = getRandomAircraftModel();
        if (reservedSeats > this.aircraftModel.getTotalSeats()) {
            throw new IllegalArgumentException("Reserved seats cannot be greater than total seats.");
        } else {
            this.availableSeats = this.aircraftModel.getTotalSeats() - this.reservedSeats;
        }
        this.flightDuration = calculateFlightDuration(departureDateTime,arrivalDateTime);

    }

    public Flight(String flightCode,AirlineCompany airlineCompany, String departureCity, String arrivalCity, ZonedDateTime departureDateTime, ZonedDateTime arrivalDateTime, int reservedSeats, BigDecimal price) {
        this.flightCode = flightCode;
        this.airlineCompany = airlineCompany;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.reservedSeats = reservedSeats;
        this.price = price;
        this.aircraftModel = getRandomAircraftModel();
        if (reservedSeats > this.aircraftModel.getTotalSeats()) {
            throw new IllegalArgumentException("Reserved seats cannot be greater than total seats.");
        } else {
            this.availableSeats = this.aircraftModel.getTotalSeats() - this.reservedSeats;
        }
        this.flightDuration = calculateFlightDuration(departureDateTime,arrivalDateTime);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public ZonedDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(ZonedDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public ZonedDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(ZonedDateTime arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public Duration getFlightDuration() {
        return flightDuration;
    }

    public void setFlightDuration(Duration flightDuration) {
        this.flightDuration = flightDuration;
    }

    public AirCraftModel getAircraftModel() {
        return aircraftModel;
    }

    public void setAircraftModel(AirCraftModel aircraftModel) {
        this.aircraftModel = aircraftModel;
    }

    public int getReservedSeats() {
        return reservedSeats;
    }

    public void setReservedSeats(int reservedSeats) {
        this.reservedSeats = reservedSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public AirlineCompany getAirlineCompany() {
        return airlineCompany;
    }

    public void setAirlineCompany(AirlineCompany airlineCompany) {
        this.airlineCompany = airlineCompany;
    }

    private AirCraftModel getRandomAircraftModel() {
        List<AirCraftModel> allModels = List.of(AirCraftModel.values());
        var random = new Random();
        Collections.shuffle(allModels, random);
        return allModels.get(0);
    }

    private Duration calculateFlightDuration(ZonedDateTime departure, ZonedDateTime arrival) {
        return Duration.between(departure, arrival);
    }
}
