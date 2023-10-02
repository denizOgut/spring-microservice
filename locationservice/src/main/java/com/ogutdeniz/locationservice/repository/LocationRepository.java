package com.ogutdeniz.locationservice.repository;

import com.ogutdeniz.locationservice.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends JpaRepository <Location,Long> {
    Optional<Location> findLocationByCity(String city);
    Optional<Location> findLocationByLongitudeAndLatitude(Double longitude, Double latitude);

    List<Location> findAllLocationsByCountry(String country);

    List<Location> findAllLocationsByContinent(String continent);
}
