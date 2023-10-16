package com.ogutdeniz.weatherservice.dto;

public record Condition(
        String text,
        String icon,
        int code) {
}
