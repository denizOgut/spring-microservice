package com.ogutdeniz.weatherservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ogutdeniz.weatherservice.dto.WeatherDto;
import com.ogutdeniz.weatherservice.dto.WeatherResponse;
import com.ogutdeniz.weatherservice.exception.JsonParseException;
import com.ogutdeniz.weatherservice.exception.WeatherDataNotFoundException;
import com.ogutdeniz.weatherservice.model.WeatherEntity;
import com.ogutdeniz.weatherservice.repository.WeatherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static com.ogutdeniz.weatherservice.constant.Constant.*;

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
        return weatherRepository.findFirstByCityOrderByResponseTimeDesc(city)
                                .map(weatherEntity -> {
                                    logger.info("Weather data for city '{}' found in the database.", city);
                                    return WeatherDto.convertToWeatherDto(weatherEntity);
                                })
                                .orElseGet(() -> {
                                    logger.info("Weather data for city '{}' not found in the database. Fetching from API...", city);
                                    try {
                                        WeatherEntity newWeatherEntity = getCurrentWeatherFromAPI(city).block();
                                        weatherRepository.save(newWeatherEntity);
                                        logger.info("Weather data for city '{}' fetched from API and saved to the database.", city);
                                        return WeatherDto.convertToWeatherDto(newWeatherEntity);
                                    } catch (Exception e) {
                                        throw new WeatherDataNotFoundException("Weather data not found for city '" + city + "'.");
                                    }
                                });
    }


    private Mono<WeatherEntity> getCurrentWeatherFromAPI(String city) {
        String uri = getWeatherStackUri(city);
        logger.info("getCurrentWeatherFromAPI method started.");

        return webClient.get()
                        .uri(uri)
                        .exchangeToMono(clientResponse -> {
                            if (clientResponse.statusCode().is4xxClientError() || clientResponse.statusCode().is5xxServerError()) {
                                return Mono.error(new WeatherDataNotFoundException("Weather data not found for city '" + city + "'."));
                            }
                            return clientResponse.bodyToMono(String.class);
                        })
                        .flatMap(jsonResponse -> {
                            try {
                                WeatherResponse weatherResponse = objectMapper.readValue(jsonResponse, WeatherResponse.class);
                                if (weatherResponse == null || weatherResponse.location() == null || weatherResponse.current() == null) {
                                    return Mono.error(new WeatherDataNotFoundException("Weather data not found for city '" + city + "'."));
                                }
                                return Mono.just(weatherResponse.convertToWeatherEntity());
                            } catch (Exception e) {
                                logger.error("Problem occurs while parsing JSON", e);
                                return Mono.error(new JsonParseException("Problem occurs while parsing JSON!"));
                            }
                        });
    }


    private String getWeatherStackUri(String city) {
        return WEATHER_OPEN_API_BASE_URL + WEATHER_OPEN_API_ACCESS_KEY_PARAM + API_KEY + WEATHER_OPEN_API_QUERY_PARAM + city;
    }
}
