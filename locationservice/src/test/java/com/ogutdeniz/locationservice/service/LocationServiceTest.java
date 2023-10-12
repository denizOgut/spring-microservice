package com.ogutdeniz.locationservice.service;

import com.ogutdeniz.locationservice.client.WeatherClient;
import com.ogutdeniz.locationservice.dto.APIResponseDto;
import com.ogutdeniz.locationservice.dto.LocationDto;
import com.ogutdeniz.locationservice.dto.WeatherDto;
import com.ogutdeniz.locationservice.exception.CityNotFoundException;
import com.ogutdeniz.locationservice.model.Location;
import com.ogutdeniz.locationservice.repository.LocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LocationServiceTest {

    @Mock
    private LocationRepository locationRepository;

    @Mock
    private WeatherClient weatherClient;

    @InjectMocks
    private LocationService locationService;

    private Optional<Location> location;

    @BeforeEach
    public void setup() {
        location = Optional.of(new Location("Istanbul", "Turkey", "Asia", 1.0, 1.0));
    }

    @Test
    public void givenCity_whenFindLocationByCity_thenReturnAPIResponseDto() {
        // given - precondition or setup
        given(locationRepository.findLocationByCity(Mockito.anyString())).willReturn(Optional.of(location.get()));

        var weatherDto = new WeatherDto("Istanbul", "Turkey", 25.0, 15.0, "Sunny", LocalDateTime.now());
        given(weatherClient.getWeather(Mockito.anyString())).willReturn(weatherDto);

        // when - action or the behavior that we are going to test
        var apiResponseDto = locationService.findLocationByCity("Istanbul");

        // then - verify the output
        assertThat(apiResponseDto).isNotNull();
    }

    @Test
    public void givenNonExistingCity_whenFindLocationByCity_thenThrowCityNotFoundException() {
        // given - precondition or setup
        given(locationRepository.findLocationByCity("Istanbul")).willReturn(Optional.empty());

        // when - action or the behavior that we are going to test
        org.junit.jupiter.api.Assertions.assertThrows(CityNotFoundException.class, () -> {
            locationService.findLocationByCity("NonExistingCity");
        });

        // then - verify that the exception is thrown
        verify(weatherClient, never()).getWeather(Mockito.anyString());
    }

    @Test
    public void givenLongitudeAndLatitude_whenFindLocationByLongitudeAndLatitude_thenReturnAPIResponseDto() {
        // given - precondition or setup
        given(locationRepository.findLocationByLongitudeAndLatitude(1.0, 1.0)).willReturn(Optional.of(location.get()));

        WeatherDto weatherDto = new WeatherDto("Istanbul", "Turkey", 25.0, 15.0, "Sunny", LocalDateTime.now());
        given(weatherClient.getWeather(Mockito.anyString())).willReturn(weatherDto);

        // when - action or the behavior that we are going to test
        APIResponseDto apiResponseDto = locationService.findLocationByLongitudeAndLatitude(12.34, 56.78);

        // then - verify the output
        assertThat(apiResponseDto).isNotNull();;
    }

    @Test
    public void givenCountry_whenFindAllLocationsByCountry_thenReturnListAPIResponseDto() {
        // given - precondition or setup
        given(locationRepository.findAllLocationsByCountry("Turkey")).willReturn(Collections.singletonList(location.get()));

        WeatherDto weatherDto = new WeatherDto("Istanbul", "Turkey", 25.0, 15.0, "Sunny", LocalDateTime.now());
        given(weatherClient.getWeather(Mockito.anyString())).willReturn(weatherDto);

        // when - action or the behavior that we are going to test
        List<APIResponseDto> apiResponseDtoList = locationService.findAllLocationsByCountry("Turkey");

        // then - verify the output
        assertThat(apiResponseDtoList).isNotNull();
        assertThat(apiResponseDtoList.size()).isEqualTo(1);
    }

    @Test
    public void givenContinent_whenFindAllLocationsByContinent_thenReturnListAPIResponseDto() {
        // given - precondition or setup
        given(locationRepository.findAllLocationsByContinent("Asia")).willReturn(Collections.singletonList(location.get()));

        WeatherDto weatherDto = new WeatherDto("Istanbul", "Turkey", 25.0, 15.0, "Sunny", LocalDateTime.now());
        given(weatherClient.getWeather(Mockito.anyString())).willReturn(weatherDto);

        // when - action or the behavior that we are going to test
        List<APIResponseDto> apiResponseDtoList = locationService.findAllLocationsByContinent("Asia");

        // then - verify the output
        assertThat(apiResponseDtoList).isNotNull();
        assertThat(apiResponseDtoList.size()).isEqualTo(1);
    }


    @Test
    public void givenLocation_whenSaveLocation_thenReturnLocationDto() {
        // given - precondition or setup
        given(locationRepository.save(any(Location.class))).willReturn(location.get());

        // when - action or the behavior that we are going to test
        LocationDto savedLocationDto = locationService.saveLocation(location.get());

        // then - verify the output
        assertThat(savedLocationDto).isNotNull();
        assertThat(savedLocationDto.city()).isEqualTo("Istanbul");
    }
}