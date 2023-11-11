package com.ogutdeniz.flightservice.service;

import com.ogutdeniz.flightservice.client.LocationClient;
import com.ogutdeniz.flightservice.constant.AirlineCompany;
import com.ogutdeniz.flightservice.dto.FlightApiResponseDto;
import com.ogutdeniz.flightservice.dto.FlightDto;
import com.ogutdeniz.flightservice.dto.LocationApiResponseDto;
import com.ogutdeniz.flightservice.exception.FlightNotFoundException;
import com.ogutdeniz.flightservice.model.Flight;
import com.ogutdeniz.flightservice.repository.FlightRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
    private final FlightRepository flightRepository;
    private final LocationClient locationClient;

    private static final Logger logger = LoggerFactory.getLogger(FlightService.class);

    public FlightService(FlightRepository flightRepository, LocationClient locationClient) {
        this.flightRepository = flightRepository;
        this.locationClient = locationClient;
    }

    public FlightApiResponseDto findFlightByFlightCode(String flightCode) {
        var flight = flightRepository.findFlightByFlightCode(flightCode)
                                     .orElseThrow(() -> new FlightNotFoundException("The flight not found by given flightCode: " + flightCode));
        var departureLocationDto = locationClient.findLocationByCountryAndCity(flight.getDepartureCountry(), flight.getDepartureCity());
        var arrivalLocationDto = locationClient.findLocationByCountryAndCity(flight.getArrivalCountry(), flight.getArrivalCity());
        var flightDto = FlightDto.convertToFlightDto(flight);

        return new FlightApiResponseDto(flightDto, departureLocationDto, arrivalLocationDto);
    }

    public List<FlightApiResponseDto> findAllFlightsByFlightDurationGreaterThanEqual(Long duration) {

        var dtoList = flightRepository.findAllFlightsByFlightDurationGreaterThanEqual(Duration.ofHours(duration))
                                         .stream()
                                         .map(FlightDto::convertToFlightDto)
                                         .toList();

        return dtoList.stream()
                         .map(flightDto -> {
                             LocationApiResponseDto departureLocationApiResponseDtoDto = locationClient.findLocationByCountryAndCity(flightDto.departureCountry(), flightDto.departureCity());
                             LocationApiResponseDto arrivalLocationApiResponseDtoDto = locationClient.findLocationByCountryAndCity(flightDto.arrivalCountry(), flightDto.arrivalCity());
                             return new FlightApiResponseDto(flightDto, departureLocationApiResponseDtoDto, arrivalLocationApiResponseDtoDto);
                         }).toList();
    }


    public List<FlightApiResponseDto> findAllFlightsByArrivalCountryAndArrivalCityAndAirlineCompany(String arrivalCountry, String arrivalCity, AirlineCompany airlineCompany) {
        var dtoList = flightRepository.findAllFlightsByArrivalCountryAndArrivalCityAndAirlineCompany(arrivalCountry, arrivalCity, airlineCompany)
                                         .stream()
                                         .map(FlightDto::convertToFlightDto)
                                         .toList();

        return dtoList.stream()
                         .map(flightDto -> {
                             LocationApiResponseDto departureLocationApiResponseDtoDto = locationClient.findLocationByCountryAndCity(flightDto.departureCountry(), flightDto.departureCity());
                             LocationApiResponseDto arrivalLocationApiResponseDtoDto = locationClient.findLocationByCountryAndCity(flightDto.arrivalCountry(), flightDto.arrivalCity());
                             return new FlightApiResponseDto(flightDto, departureLocationApiResponseDtoDto, arrivalLocationApiResponseDtoDto);
                         }).toList();
    }

    public FlightDto saveFlight(Flight flight) {
        var savedFlight = flightRepository.save(flight);
        return FlightDto.convertToFlightDto(savedFlight);
    }
}
