package com.ogutdeniz.flightservice.dto;



public record LocationDto(String city,
                          String country,
                          String continent,
                          double latitude,
                          double longitude) {
}
