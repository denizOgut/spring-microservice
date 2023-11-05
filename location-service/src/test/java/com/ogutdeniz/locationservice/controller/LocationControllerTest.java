package com.ogutdeniz.locationservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ogutdeniz.locationservice.dto.LocationApiResponseDto;
import com.ogutdeniz.locationservice.dto.LocationDto;
import com.ogutdeniz.locationservice.model.Location;
import com.ogutdeniz.locationservice.service.LocationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest
class LocationControllerTest {

    @Mock
    private LocationService locationService;

    @InjectMocks
    private LocationController locationController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;


    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(locationController).build();
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void givenCity_whenFindLocationByCity_thenReturnAPIResponseDto() throws Exception {
        // given - precondition or setup
        String cityName = "Istanbul";
        String countryName = "Turkey";
        LocationApiResponseDto locationApiResponseDto = new LocationApiResponseDto(new LocationDto(cityName, "Turkey", "Asia", 41.0082, 28.9784), null);
        given(locationService.findLocationByCountryAndCity(countryName,cityName)).willReturn(locationApiResponseDto);

        // when -  action or the behaviour that we are going test
        ResultActions resultActions = mockMvc.perform(get("/api/v1/location-service/{city}", cityName)
                .contentType(MediaType.APPLICATION_JSON));

        // then - verify the output
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.locationDto.city").value(cityName));
    }

    @Test
    public void givenLongitudeAndLatitude_whenFindLocationByLongitudeAndLatitude_thenReturnAPIResponseDto() throws Exception {
        // given - precondition or setup
        Double longitude = 28.9784;
        Double latitude = 41.0082;
        LocationApiResponseDto locationApiResponseDto = new LocationApiResponseDto(new LocationDto("Istanbul", "Turkey", "Asia", longitude, latitude), null);
        given(locationService.findLocationByLongitudeAndLatitude(longitude, latitude)).willReturn(locationApiResponseDto);

        // when -  action or the behaviour that we are going test
        ResultActions resultActions = mockMvc.perform(get("/api/v1/location-service/{longitude}/{latitude}", longitude, latitude)
                .contentType(MediaType.APPLICATION_JSON));

        // then - verify the output
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.locationDto.longitude").value(longitude))
                .andExpect(jsonPath("$.locationDto.latitude").value(latitude));
    }

    @Test
    public void givenCountry_whenFindAllLocationsByCountry_thenReturnListAPIResponseDto() throws Exception {
        // given - precondition or setup
        String countryName = "Turkey";
        List<LocationApiResponseDto> locationApiResponseDtoList = Collections.singletonList(new LocationApiResponseDto(new LocationDto("Istanbul", countryName, "Asia", 41.0082, 28.9784), null));
        given(locationService.findAllLocationsByCountry(countryName)).willReturn(locationApiResponseDtoList);

        // when -  action or the behaviour that we are going test
        ResultActions resultActions = mockMvc.perform(get("/api/v1/location-service/{country}", countryName)
                .contentType(MediaType.APPLICATION_JSON));

        // then - verify the output
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].locationDto.country").value(countryName));
    }

    @Test
    public void givenContinent_whenFindAllLocationsByContinent_thenReturnListAPIResponseDto() throws Exception {
        // given - precondition or setup
        String continentName = "Asia";
        List<LocationApiResponseDto> locationApiResponseDtoList = Collections.singletonList(new LocationApiResponseDto(new LocationDto("Istanbul", "Turkey", continentName, 41.0082, 28.9784), null));
        given(locationService.findAllLocationsByCountry(continentName)).willReturn(locationApiResponseDtoList);

        // when -  action or the behaviour that we are going test
        ResultActions resultActions = mockMvc.perform(get("/api/v1/location-service/{continent}", continentName)
                .contentType(MediaType.APPLICATION_JSON));

        // then - verify the output
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].locationDto.continent").value(continentName));
    }

    @Test
    public void givenLocation_whenSaveLocation_thenReturnLocationDto() throws Exception {
        // given - precondition or setup
        Location location = new Location("Istanbul", "Turkey", "Asia", 41.0082, 28.9784);
        LocationDto locationDto = new LocationDto("Istanbul", "Turkey", "Asia", 41.0082, 28.9784);
        given(locationService.saveLocation(any(Location.class))).willReturn(locationDto);

        // when -  action or the behaviour that we are going test
        ResultActions resultActions = mockMvc.perform(post("/api/v1/location-service/locations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(location)));

        // then - verify the output
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.city").value("Istanbul"));
    }
}