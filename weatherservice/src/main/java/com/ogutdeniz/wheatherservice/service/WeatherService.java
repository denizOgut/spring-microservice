package com.ogutdeniz.wheatherservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ogutdeniz.wheatherservice.dto.WeatherDto;
import com.ogutdeniz.wheatherservice.exception.JsonParseException;
import com.ogutdeniz.wheatherservice.exception.WeatherDataNotFoundException;
import com.ogutdeniz.wheatherservice.model.WeatherEntity;
import com.ogutdeniz.wheatherservice.repository.WeatherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.Optional;

import static com.ogutdeniz.wheatherservice.constant.Constant.*;

@Service
public class WeatherService {
    private final WeatherRepository weatherRepository;
    private final WebClient webClient;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

    public WeatherService(WeatherRepository weatherRepository, WebClient webClient) {
        this.weatherRepository = weatherRepository;
        this.webClient = webClient;
    }

    public WeatherDto getWeather(String city) {
        boolean existsInDatabase = weatherRepository.existsByCityName(city);
        WeatherEntity weatherEntity;
        if (!existsInDatabase) {
            logger.info("Weather data for city '{}' not found in the database. Fetching from API...", city);
            weatherEntity = getCurrentWeatherFromAPI(city).block();
            weatherRepository.save(Objects.requireNonNull(weatherEntity));
            logger.info("Weather data for city '{}' fetched from API and saved to the database.", city);
        } else {
            logger.info("Weather data for city '{}' found in the database.", city);
            Optional<WeatherEntity> weatherEntityOptional = Optional.ofNullable(weatherRepository.findByCityName(city));
            weatherEntity = weatherEntityOptional.orElseThrow(() ->
                    new WeatherDataNotFoundException("Weather data for city '" + city + "' not found."));
        }


        return WeatherDto.convertToWeatherDto(weatherEntity);
    }

    public Mono<WeatherEntity> getCurrentWeatherFromAPI(String cityName) {
        String uri = getWeatherStackUri(cityName);
        logger.info("getCurrentWeatherFromAPI method started.");

        return webClient.get()
                        .uri(uri)
                        .retrieve()
                        .bodyToMono(String.class)
                        .flatMap(jsonResponse -> {
                            try {
                                WeatherEntity weatherEntity = objectMapper.readValue(jsonResponse, WeatherEntity.class);
                                return Mono.just(weatherEntity);
                            } catch (Exception e) {
                                logger.error("Problem occurs while parsing JSON", e);
                                return Mono.error(new JsonParseException("Problem occurs while parsing !"));
                            }
                        });
    }

    private String getWeatherStackUri(String city) {
        return WEATHER_OPEN_API_BASE_URL + WEATHER_OPEN_API_ACCESS_KEY_PARAM + API_KEY + WEATHER_OPEN_API_QUERY_PARAM + city;
    }
}