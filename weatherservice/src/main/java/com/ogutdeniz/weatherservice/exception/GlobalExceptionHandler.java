package com.ogutdeniz.weatherservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(JsonParseException.class)
    public ProblemDetail jsonParseException(JsonParseException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_MODIFIED, exception.getMessage());
        problemDetail.setTitle("JSON Parsing Error");
        problemDetail.setType(URI.create("https://http.cat/status/404"));
        problemDetail.setProperty("errorCategory", "Generic");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    @ExceptionHandler(WeatherDataNotFoundException.class)
    public ProblemDetail weatherDataNotFoundException(WeatherDataNotFoundException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage());
        problemDetail.setTitle("Weather Not Found");
        problemDetail.setType(URI.create("https://http.cat/status/404"));
        problemDetail.setProperty("errorCategory", "Generic");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }
}




