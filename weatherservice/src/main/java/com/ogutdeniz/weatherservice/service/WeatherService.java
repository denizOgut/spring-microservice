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
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;
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

    @Cacheable(value = "weather", key = "#city")
    public WeatherDto getWeather(String city) {
        Optional<WeatherEntity> weatherEntityOptional = weatherRepository.findFirstByCityOrderByResponseTimeDesc(city);
        WeatherEntity weatherEntity;
        if (!weatherEntityOptional.isPresent() ||
                hasCacheExpired(weatherEntityOptional.get().getResponseTime())) {

            String logMsg = !weatherEntityOptional.isPresent() ?
                    String.format("No weather data for city '%s' found in the database. Fetching from the external API...", city) :
                    String.format("Weather data for city '%s' is outdated (exceeded %d hours). Fetching from the external API...", city, CACHE_TIME_LIMIT_IN_HOURS);

            logger.info(logMsg);

            try {
                weatherEntity = getCurrentWeatherFromAPI(city).block();
                weatherRepository.save(Objects.requireNonNull(weatherEntity));
                logger.info("Weather data for city '{}' fetched from API and saved to the database.", city);

            } catch (Exception e) {
                throw new WeatherDataNotFoundException("Weather data not found for city '" + city + "'.");
            }
        } else {
            weatherEntity = weatherEntityOptional.get();
            logger.info("Weather data for city '{}' found in the database.", city);
        }
        return WeatherDto.convertToWeatherDto(weatherEntity);
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

    private boolean hasCacheExpired(LocalDateTime lastResponseTime) {
        long hoursElapsedSinceLastResponse = Duration.between(lastResponseTime, LocalDateTime.now()).toHours();
        return hoursElapsedSinceLastResponse >= CACHE_TIME_LIMIT_IN_HOURS;
    }


    private String getWeatherStackUri(String city) {
        return WEATHER_OPEN_API_BASE_URL + WEATHER_OPEN_API_ACCESS_KEY_PARAM + API_KEY + WEATHER_OPEN_API_QUERY_PARAM + city;
    }
}
