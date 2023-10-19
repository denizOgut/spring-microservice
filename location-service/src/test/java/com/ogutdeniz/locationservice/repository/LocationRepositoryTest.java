package com.ogutdeniz.locationservice.repository;

import com.ogutdeniz.locationservice.model.Location;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class LocationRepositoryTest {

    @Autowired
    private LocationRepository locationRepository;
    private Optional<Location> location;

    @BeforeEach
    void setUp() {
        location = Optional.of(new Location("Istanbul", "Turkey", "Asia", 1.0, 1.0));
    }

    @Test
    public void givenCity_whenFindLocationByCity_thenReturnLocation() {
        // given - precondition or setup
            locationRepository.save(location.get());
        // when -  action or the behaviour that we are going test
        var locationFromDb = locationRepository.findLocationByCountryAndCity(location.get().getCountry(),location.get().getCity());
        // then - verify the output
        Assertions.assertEquals(location,locationFromDb);
    }

    @Test
    public void givenLongitudeAndLatitude_whenfindLocationByLongitudeAndLatitude_thenReturnLocation() {
        // given - precondition or setup
        locationRepository.save(location.get());
        // when -  action or the behaviour that we are going test
        var locationFromDb = locationRepository.findLocationByLongitudeAndLatitude(location.get().getLongitude(),location.get().getLatitude());
        // then - verify the output
        Assertions.assertEquals(location,locationFromDb);
    }

    @Test
    public void givenCountry_whenFindAllLocationsByCountry_thenLocationList(String country){
        // given - precondition or setup
        Location location1 = new Location("Ankara", "Turkey", "Asia", 2.0, 2.0);

        locationRepository.save(location.get());
        locationRepository.save(location1);

        // when -  action or the behaviour that we are going test
        List<Location> locationList = locationRepository.findAllLocationsByCountry(country);

        // then - verify the output
        assertThat(locationList).isNotNull();
        assertThat(locationList.size()).isEqualTo(2);

    }

    @Test
    public void givenCountry_whenFindAllLocationsByContinent_thenLocationList(String continent){
        // given - precondition or setup
        Location location1 = new Location("Ankara", "Turkey", "Asia", 2.0, 2.0);

        locationRepository.save(location.get());
        locationRepository.save(location1);

        // when -  action or the behaviour that we are going test
        List<Location> locationList = locationRepository.findAllLocationsByContinent(continent);

        // then - verify the output
        assertThat(locationList).isNotNull();
        assertThat(locationList.size()).isEqualTo(2);

    }
}