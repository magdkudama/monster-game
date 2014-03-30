package com.magdkudama.monstergame.model.neighbor;

public enum NeighborLocation {
    NORTH("north"),
    SOUTH("south"),
    EAST("east"),
    WEST("west");

    protected String locationName;

    private NeighborLocation(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationName() {
        return locationName;
    }

    public boolean equals(NeighborLocation neighborLocation) {
        return locationName.equals(neighborLocation.getLocationName());
    }
}
