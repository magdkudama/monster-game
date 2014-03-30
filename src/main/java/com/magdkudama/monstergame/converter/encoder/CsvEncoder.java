package com.magdkudama.monstergame.converter.encoder;

import au.com.bytecode.opencsv.CSVWriter;
import com.magdkudama.monstergame.converter.Encoder;
import com.magdkudama.monstergame.converter.encoder.exception.WriteException;
import com.magdkudama.monstergame.model.City;
import com.magdkudama.monstergame.model.neighbor.NeighborLocation;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CsvEncoder implements Encoder {
    private CSVWriter writer;

    public CsvEncoder(FileWriter file) {
        writer = new CSVWriter(file, ';');
    }

    @Override
    public void encode(List<City> cities) throws WriteException, IOException {
        List<String> columns;

        for (City city : cities) {
            columns = new ArrayList<>();
            columns.add(city.getName());
            for (Map.Entry<NeighborLocation, City> neighbor : city.getNeighbors().entrySet()) {
                columns.add(neighbor.getKey().getLocationName() + "=" + neighbor.getValue().getName());
            }
            writer.writeNext(columns.toArray(new String[columns.size()]));
        }

        writer.close();
    }
}
