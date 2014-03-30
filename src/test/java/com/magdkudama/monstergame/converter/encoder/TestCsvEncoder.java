package com.magdkudama.monstergame.converter.encoder;

import com.magdkudama.monstergame.Utils;
import com.magdkudama.monstergame.converter.Encoder;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class TestCsvEncoder {
    private static String MAP_RESULT = Utils.testsDirectory() + "converter/encoder/results/result.csv";
    private static String MAP_EXPECTED = Utils.testsDirectory() + "converter/encoder/fixture/map.csv";
    private Encoder encoder;

    @Before
    public void setUp() throws IOException {
        encoder = new CsvEncoder(new FileWriter(MAP_RESULT));
    }

    @After
    public void tearDown() {
        File file = new File(MAP_RESULT);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testEncodingToCsvWorks() throws IOException {
        encoder.encode(Utils.getTestWorld().getCities());
        assertTrue(new File(MAP_RESULT).exists());

        File result = new File(MAP_EXPECTED);
        File expected = new File(MAP_RESULT);

        assertTrue(FileUtils.contentEquals(expected, result));
    }
}
