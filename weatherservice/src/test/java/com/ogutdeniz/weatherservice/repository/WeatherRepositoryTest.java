package com.ogutdeniz.weatherservice.repository;

import com.ogutdeniz.weatherservice.model.WeatherEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class WeatherRepositoryTest {

    @Autowired
    private WeatherRepository weatherRepository;

    private Optional<WeatherEntity> weatherEntity;

    @BeforeEach
    void setUp() {
        weatherEntity = Optional.of(new WeatherEntity("TestCity", "TestCountry", 25.5, 15.0, "Sunny"));
    }

    @Test
    public void givenWeatherEntity_whenFindFirstByCityOrderByResponseTimeDesc_thenReturnWeatherEntity() {

        // given - precondition or setup
        weatherRepository.save(weatherEntity.get());
        // when -  action or the behaviour that we are going test
        var weatherEntityFromDB = weatherRepository.findFirstByCityOrderByResponseTimeDesc(weatherEntity.get().getCity());
        // then - verify the output
        Assertions.assertNotNull(weatherEntityFromDB);

    }

    @Test
    public void givenWeatherEntity_whenNotFindFirstByCityOrderByResponseTimeDesc_thenEmpty() {
        // given - precondition or setup

        // when -  action or the behaviour that we are going test
        var weatherEntityFromDB = weatherRepository.findFirstByCityOrderByResponseTimeDesc("NonExistentCity");
        // then - verify the output
        assertThat(weatherEntityFromDB).isEmpty();
    }
}