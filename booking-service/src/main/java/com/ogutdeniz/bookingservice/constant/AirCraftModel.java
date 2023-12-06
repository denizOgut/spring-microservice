package com.ogutdeniz.bookingservice.constant;

public enum AirCraftModel {

    BOEING_747(524),
    AIRBUS_A320(220),
    BOEING_777(396),
    AIRBUS_A380(853),
    EMBRAER_ERJ145(50),
    CESSNA_172(4),
    AIRBUS_A350(440),
    BOEING_737(230);

    private final int totalSeats;

    AirCraftModel(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getTotalSeats() {
        return totalSeats;
    }
}
