package com.magdkudama.monstergame.model.neighbor.exception;

import java.security.InvalidKeyException;

public class NeighborPositionNotFoundException extends InvalidKeyException {
    public NeighborPositionNotFoundException(String message) {
        super(message);
    }
}
