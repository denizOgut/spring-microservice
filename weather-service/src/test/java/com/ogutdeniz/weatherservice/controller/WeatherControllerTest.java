package com.ogutdeniz.weatherservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ogutdeniz.weatherservice.dto.WeatherDto;
import com.ogutdeniz.weatherservice.model.Weather;
import com.ogutdeniz.weatherservice.service.WeatherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherService weatherService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {

    }

    @Test
    public void givenCity_whenGetWeather_thenReturnWeatherDto() throws Exception{
        // given - precondition or setup
        var weatherEntity = Optional.of(
                new Weather("TestCity", "TestCountry", 25.5, 15.0, "Sunny"));

        given(weatherService.getWeather(weatherEntity.get().getCity())).willReturn(WeatherDto.convertToWeatherDto(weatherEntity.get()));

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("v1/api/open-weather/{city}", weatherEntity.get().getCity()));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.city", is(weatherEntity.get().getCity())))
                .andExpect(jsonPath("$.country", is(weatherEntity.get().getCountry())))
                .andExpect(jsonPath("$.temperature", is(weatherEntity.get().getTemperature())))
                .andExpect(jsonPath("$.windSpeedInKph", is(weatherEntity.get().getWindSpeedInKph())))
                .andExpect(jsonPath("$.weatherCondition", is(weatherEntity.get().getWeatherCondition())))
                .andExpect(jsonPath("$.responseTime", is(weatherEntity.get().getResponseTime())));

    }
}