package com.ogutdeniz.weatherservice.dto;


import com.ogutdeniz.weatherservice.model.Weather;



public record WeatherResponse(Location location,
                              Current current) {
    public Weather convertToWeatherEntity() {
        Weather weather = new Weather();
        weather.setCity(location.name());
        weather.setCountry(location.country());
        weather.setTemperature(current.temp_c());
        weather.setWindSpeedInKph(current.wind_kph());
        weather.setWeatherCondition(current.condition().text());


        return weather;
    }


}
