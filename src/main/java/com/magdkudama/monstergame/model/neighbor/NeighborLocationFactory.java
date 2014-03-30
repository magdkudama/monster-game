package com.magdkudama.monstergame.model.neighbor;

import com.magdkudama.monstergame.model.neighbor.exception.NeighborPositionNotFoundException;

public class NeighborLocationFactory {
    protected NeighborLocationFactory() {
    }

    public static NeighborLocation create(String location) throws NeighborPositionNotFoundException {
        for (NeighborLocation neighborLocation : NeighborLocation.values()) {
            if (neighborLocation.getLocationName().equals(location)) {
                return neighborLocation;
            }
        }

        throw new NeighborPositionNotFoundException("Invalid position " + location);
    }
}
