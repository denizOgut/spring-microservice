package com.ogutdeniz.weatherservice.runner;

import com.ogutdeniz.weatherservice.service.WeatherService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WeatherDataCleanupTask {

    private final WeatherService weatherService;

    public WeatherDataCleanupTask(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteWeatherDataJob() {
        weatherService.deleteAllWeatherData();
    }
}
