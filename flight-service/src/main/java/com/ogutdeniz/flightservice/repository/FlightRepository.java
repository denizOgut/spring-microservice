package com.ogutdeniz.flightservice.repository;

import com.ogutdeniz.flightservice.constant.AirlineCompany;
import com.ogutdeniz.flightservice.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    Optional<Flight> findFlightByFlightCode(String flightCode);

    List<Flight> findAllFlightsByFlightDurationGreaterThanEqual(Duration duration);

    List<Flight> findAllFlightsByArrivalCityAndAirlineCompany(String arrivalCountry,String arrivalCity, AirlineCompany airlineCompany);
}
