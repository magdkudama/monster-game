package com.magdkudama.monstergame.converter;

import com.magdkudama.monstergame.converter.decoder.exception.ReadException;
import com.magdkudama.monstergame.model.World;

public interface Decoder {
    public World decode() throws ReadException;
}
