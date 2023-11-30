package com.ogutdeniz.bookingservice.repository;

import com.ogutdeniz.bookingservice.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface BookingRepository extends MongoRepository<Booking, String> {
    Optional<Booking> findByBookingCode(UUID bookingCode);
}
