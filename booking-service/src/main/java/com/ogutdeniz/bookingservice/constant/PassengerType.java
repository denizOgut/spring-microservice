package com.ogutdeniz.bookingservice.constant;

public enum PassengerType {
    ADULT("Adult"),
    CHILD("Child"),
    INFANT("Infant");

    private final String displayName;

    PassengerType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
