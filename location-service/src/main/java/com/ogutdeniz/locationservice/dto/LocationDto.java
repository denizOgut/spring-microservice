package com.ogutdeniz.locationservice.dto;

import com.ogutdeniz.locationservice.model.Location;

public record LocationDto(String city,
                          String country,
                          String continent,
                          double latitude,
                          double longitude) {

    public static LocationDto converToLocationDto(Location from) {
        return new LocationDto(from.getCity(),
                from.getCountry(),
                from.getContinent(),
                from.getLatitude(),
                from.getLongitude());
    }
}
