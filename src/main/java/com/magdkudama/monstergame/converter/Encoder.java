package com.magdkudama.monstergame.converter;

import com.magdkudama.monstergame.converter.encoder.exception.WriteException;
import com.magdkudama.monstergame.model.City;

import java.io.IOException;
import java.util.List;

public interface Encoder {
    public void encode(List<City> cities) throws WriteException, IOException;
}
