package com.ogutdeniz.bookingservice.dto;



public record LocationDto(String city,
                          String country,
                          String continent,
                          double latitude,
                          double longitude) {
}
