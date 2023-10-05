package com.ogutdeniz.locationservice.service;

import com.ogutdeniz.locationservice.client.WeatherClient;
import com.ogutdeniz.locationservice.dto.APIResponseDto;
import com.ogutdeniz.locationservice.dto.LocationDto;
import com.ogutdeniz.locationservice.dto.WeatherDto;
import com.ogutdeniz.locationservice.exception.CityNotFoundException;
import com.ogutdeniz.locationservice.repository.LocationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class LocationService {

    private static final Logger logger = LoggerFactory.getLogger(LocationService.class);
    private final LocationRepository locationRepository;
    private final WeatherClient weatherClient;

    public LocationService(LocationRepository locationRepository, WeatherClient weatherClient) {
        this.locationRepository = locationRepository;
        this.weatherClient = weatherClient;
    }

    public APIResponseDto findLocationByCity(String city) {
        logger.info("findLocationByCity method started.");
        var location = locationRepository.findLocationByCity(city).
                                         orElseThrow(() -> new CityNotFoundException("Given city is not found " + city));
        var locationDto = LocationDto.converToLocationDto(location);

        var weatherDto = weatherClient.getWeather(city);
        logger.info("findLocationByCity method ended.");
        return new APIResponseDto(locationDto, weatherDto);
    }

    public APIResponseDto findLocationByLongitudeAndLatitude(Double longitude, Double latitude) {
        logger.info("findLocationByLongitudeAndLatitude method started.");
        var location = locationRepository.findLocationByLongitudeAndLatitude(longitude, latitude).
                                         orElseThrow(() -> new CityNotFoundException("The city not found given longitude and latitude " + longitude + " " + latitude));

        var locationDto = LocationDto.converToLocationDto(location);

        var weatherDto = weatherClient.getWeather(locationDto.city());
        logger.info("findLocationByLongitudeAndLatitude method ended.");
        return new APIResponseDto(locationDto, weatherDto);
    }

    public List<APIResponseDto> findAllLocationsByCountry(String country) {
        logger.info("findAllLocationsByCountry method started.");
        var locationDtoList = locationRepository.findAllLocationsByCountry(country)
                                                .stream()
                                                .map(LocationDto::converToLocationDto)
                                                .toList();

        logger.info("findAllLocationsByCountry method ended after streaming.");
        return locationDtoList
                .stream()
                .map(locationDto -> {
                    WeatherDto weatherDto = weatherClient.getWeather(locationDto.city());
                    return new APIResponseDto(locationDto, weatherDto);
                })
                .toList();


    }

    public List<APIResponseDto> findAllLocationsByContinent(String continent) {
        logger.info("findAllLocationsByContinent method started.");
        var locationDtoList = locationRepository.findAllLocationsByContinent(continent)
                                                .stream()
                                                .map(LocationDto::converToLocationDto)
                                                .toList();

        logger.info("findAllLocationsByContinent method ended after streaming.");
        return locationDtoList
                .stream()
                .map(locationDto -> {
                    WeatherDto weatherDto = weatherClient.getWeather(locationDto.city());
                    return new APIResponseDto(locationDto, weatherDto);
                })
                .toList();


    }

}
