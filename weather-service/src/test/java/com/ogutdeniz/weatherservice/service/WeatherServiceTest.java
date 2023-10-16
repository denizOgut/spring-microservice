package com.ogutdeniz.weatherservice.service;

import com.ogutdeniz.weatherservice.dto.WeatherDto;
import com.ogutdeniz.weatherservice.exception.WeatherDataNotFoundException;
import com.ogutdeniz.weatherservice.model.Weather;
import com.ogutdeniz.weatherservice.repository.WeatherRepository;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {

    @Mock
    private WeatherRepository weatherRepository;

    private Optional<Weather> weatherEntity;

    private WeatherDto weatherDto;

    @BeforeEach
    void setUp() {
        weatherEntity = Optional.of(new Weather("TestCity", "TestCountry", 25.5, 15.0, "Sunny"));
    }


    @Test
    public void givenWeatherEntity_whenGetWeatherByCity_thenReturnWeatherDto() {
        // given - precondition or setup
        given(weatherRepository.findFirstByCityOrderByResponseTimeDesc("TestCity")).willReturn(Optional.of(weatherEntity).orElse(null));

        // when - action or the behaviour that we are going to test
        WeatherDto weatherDto = WeatherDto.convertToWeatherDto(weatherRepository.findFirstByCityOrderByResponseTimeDesc("TestCity").get());

        // then - verify the output
        assertThat(weatherDto).isNotNull();
    }

    @Test
    public void givenWeatherEntityNonExists_whenGetWeatherByCity_thenReturnWeatherDataNotFoundException() {
        // given - precondition or setup
        given(weatherRepository.findFirstByCityOrderByResponseTimeDesc("NonExistentCity")).willReturn(Optional.empty());

        // when - action or the behaviour that we are going to test
        // Use assertThrows to verify that WeatherDataNotFoundException is thrown
        assertThrows(WeatherDataNotFoundException.class, () -> {
            weatherRepository.findFirstByCityOrderByResponseTimeDesc("NonExistentCity").orElseThrow();
        });
    }

}