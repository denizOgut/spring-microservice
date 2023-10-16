package com.ogutdeniz.weatherservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebfluxConfig {
    @Bean
    public WebClient getWebClient() {
        return WebClient.builder().build();
    }
}
