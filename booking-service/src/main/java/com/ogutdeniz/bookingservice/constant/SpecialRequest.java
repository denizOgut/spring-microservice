package com.ogutdeniz.bookingservice.constant;

public enum SpecialRequest {
    DIETARY_RESTRICTIONS("Dietary Restrictions"),
    WHEELCHAIR_ASSISTANCE("Wheelchair Assistance"),
    PET_ACCOMMODATION("Pet Accommodation"),
    OTHER("Other");

    private final String displayName;

    SpecialRequest(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
