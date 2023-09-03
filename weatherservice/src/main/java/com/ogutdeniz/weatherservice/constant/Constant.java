package com.ogutdeniz.weatherservice.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constant {

    private Constant() {
    }

    public static String WEATHER_OPEN_API_BASE_URL;
    public static String WEATHER_OPEN_API_ACCESS_KEY_PARAM;
    public static String WEATHER_OPEN_API_QUERY_PARAM;
    public static String API_KEY;

    @Value("${weather.open.api.base.url}")
    public void setWeatherOpenApiBaseUrl(String baseUrl) {
        WEATHER_OPEN_API_BASE_URL = baseUrl;
    }

    @Value("${weather.open.api.access.key.param}")
    public void setWeatherOpenApiAccessKeyParam(String accessKeyParam) {
        WEATHER_OPEN_API_ACCESS_KEY_PARAM = accessKeyParam;
    }

    @Value("${weather.open.api.query.param}")
    public void setWeatherOpenApiQueryParam(String queryParam) {
        WEATHER_OPEN_API_QUERY_PARAM = queryParam;
    }

    @Value("${weather.open.api.key}")
    public void setApiKey(String apiKey) {
        API_KEY = apiKey;
    }

}
