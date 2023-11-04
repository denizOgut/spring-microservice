package com.ogutdeniz.flightservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Flight Service API")
                        .version("1.0")
                        .description("""
                                This is an api provides flight info:
                                based on flight-code, and departure, arrival cities
                                """
                        )
                        .contact(new Contact()
                                .name("Deniz Öğüt")
                                .email("denizogut123@gmail.com")
                                .url("https://www.linkedin.com/in/deniz-öğüt/")
                        )
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")
                        )
                );
    }
}
