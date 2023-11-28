package com.ogutdeniz.bookingservice.repository;

import com.ogutdeniz.bookingservice.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookingRepository extends MongoRepository<Booking, String> {

}
