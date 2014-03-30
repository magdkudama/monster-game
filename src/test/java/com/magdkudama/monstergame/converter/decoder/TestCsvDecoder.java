package com.magdkudama.monstergame.converter.decoder;

import com.magdkudama.monstergame.Utils;
import com.magdkudama.monstergame.converter.Decoder;
import com.magdkudama.monstergame.converter.decoder.exception.ReadException;
import com.magdkudama.monstergame.model.World;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.Assert.assertEquals;

public class TestCsvDecoder {
    private static String MAP_OK = Utils.testsDirectory() + "converter/decoder/fixture/map-ok.csv";
    private static String MAP_KO = Utils.testsDirectory() + "converter/decoder/fixture/map-ko.csv";

    protected Decoder correctDecoder;
    protected Decoder errorDecoder;

    @Before
    public void setUp() throws FileNotFoundException {
        correctDecoder = new CsvDecoder(new FileReader(MAP_OK));
        errorDecoder = new CsvDecoder(new FileReader(MAP_KO));
    }

    @Test
    public void testNumberOfCitiesIsCorrect() throws ReadException {
        World world = correctDecoder.decode();
        assertEquals(4, world.getCities().size());
    }

    @Test
    public void testGeneratedWorldIsCorrect() throws ReadException {
        assertEquals(Utils.getTestWorld(), correctDecoder.decode());
    }

    @Test(expected = ReadException.class)
    public void testUnrecognizedPositionThrowsException() throws ReadException {
        errorDecoder.decode();
    }
}
