package com.ogutdeniz.bookingservice.controller;

import com.ogutdeniz.bookingservice.dto.BookingApiResponseDto;
import com.ogutdeniz.bookingservice.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/booking")
@Tag(name = "Booking Service API v1", description = "Booking Service API to book a flight")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Operation(
            method = "GET",
            summary = "find the booking information's based on the booking code.",
            description = "find the booking information's based on the booking code.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The information's of the booking code",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = BookingApiResponseDto.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "booking code is wrong. Re-try with a valid booking code",
                            content = @Content(schema = @Schema(hidden = true))
                    ),

                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal/External server error.",
                            content = @Content(schema = @Schema(hidden = true))
                    )
            }
    )
    @GetMapping(path = "/booking-code/{bookingCode}")
    ResponseEntity<BookingApiResponseDto> findByBookingCode(@PathVariable UUID bookingCode) {
        return ResponseEntity.ok(bookingService.findByBookingCode(bookingCode));
    }


}
