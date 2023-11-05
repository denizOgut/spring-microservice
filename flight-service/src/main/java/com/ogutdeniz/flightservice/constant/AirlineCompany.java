package com.ogutdeniz.flightservice.constant;

public enum AirlineCompany {
    EMIRATES("Emirates"),
    PEGASUS("Pegasus"),
    THY("Turkish Airlines"),
    LUFTHANSA("Lufthansa"),
    BRITISH_AIRWAYS("British Airways"),
    DELTA_AIRLINES("Delta Airlines");

    private final String displayName;

    AirlineCompany(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
