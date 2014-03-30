package com.magdkudama.monstergame.converter.decoder;

import au.com.bytecode.opencsv.CSVReader;
import com.magdkudama.monstergame.converter.Decoder;
import com.magdkudama.monstergame.converter.decoder.exception.ReadException;
import com.magdkudama.monstergame.model.City;
import com.magdkudama.monstergame.model.World;
import com.magdkudama.monstergame.model.neighbor.NeighborLocation;
import com.magdkudama.monstergame.model.neighbor.NeighborLocationFactory;
import com.magdkudama.monstergame.model.neighbor.exception.NeighborPositionNotFoundException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CsvDecoder implements Decoder {
    private CSVReader reader;

    public CsvDecoder(FileReader file) {
        reader = new CSVReader(file, ';');
    }

    @Override
    public World decode() throws ReadException {
        World world = new World();
        List<City> cities = new ArrayList<>();
        String[] line;

        try {
            while ((line = reader.readNext()) != null) {
                City city = getCityByName(line[0], cities);
                Map<NeighborLocation, City> neighbors = new LinkedHashMap<>();

                for (Integer i = 1; i < line.length; i++) {
                    String[] neighborPosition = line[i].split("=");
                    NeighborLocation neighborLocation = NeighborLocationFactory.create(neighborPosition[0]);
                    City neighborCity = getCityByName(neighborPosition[1], cities);

                    neighbors.put(neighborLocation, neighborCity);
                }

                city.setNeighbors(neighbors);
            }
        } catch (IOException | NeighborPositionNotFoundException e) {
            throw new ReadException(e);
        }

        world.setCities(cities);
        return world;
    }

    protected City getCityByName(String cityName, List<City> cities) {
        for (City city : cities) {
            if (city.getName().equals(cityName)) {
                return city;
            }
        }

        City city = new City(cityName);
        cities.add(city);
        return city;
    }
}
