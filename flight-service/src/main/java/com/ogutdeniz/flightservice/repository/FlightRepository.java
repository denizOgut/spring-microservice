package com.ogutdeniz.flightservice.repository;

import com.ogutdeniz.flightservice.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
}
