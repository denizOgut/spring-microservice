package com.ogutdeniz.weatherservice.dto;


import com.ogutdeniz.weatherservice.model.WeatherEntity;



public record WeatherResponse(Location location,
                              Current current) {
    public WeatherEntity convertToWeatherEntity() {
        WeatherEntity weatherEntity = new WeatherEntity();
        weatherEntity.setCity(location.name());
        weatherEntity.setCountry(location.country());
        weatherEntity.setTemperature(current.temp_c());
        weatherEntity.setWindSpeedInKph(current.wind_kph());
        weatherEntity.setWeatherCondition(current.condition().text());


        return weatherEntity;
    }


}
