package com.ogutdeniz.wheatherservice.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constant {

    private Constant() {
    }

    @Value("${weather.open.api.base.url}")
    public static String WEATHER_OPEN_API_BASE_URL;

    @Value("${weather.open.api.access.key.param}")
    public static String WEATHER_OPEN_API_ACCESS_KEY_PARAM;

    @Value("${weather.open.api.query.param}")
    public static String WEATHER_OPEN_API_QUERY_PARAM;

    @Value("${weather.open.api.key}")
    public static String API_KEY;

}
