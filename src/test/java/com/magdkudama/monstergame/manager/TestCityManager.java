package com.magdkudama.monstergame.manager;

import com.magdkudama.monstergame.model.City;
import com.magdkudama.monstergame.model.Monster;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestCityManager {
    private CityManager cityManager;

    @Before
    public void setUp() {
        List<City> cities = new ArrayList<>();
        cities.add(new City("A"));
        cities.add(new City("B"));
        cities.add(new City("C"));

        cityManager = new CityManager(cities);
    }

    @Test
    public void testRemainingCitiesExcludesDestroyedCities() {
        assertEquals(3, cityManager.getRemainingCities().size());
        cityManager.getRemainingCities().get(0).destroy();
        assertEquals(2, cityManager.getRemainingCities().size());
    }

    @Test
    public void testCitiesWithConflictAreThoseWithMoreThanAMonster() {
        cityManager.getCities().get(0).addMonster(new Monster(1));
        cityManager.getCities().get(0).addMonster(new Monster(2));
        cityManager.getCities().get(1).addMonster(new Monster(3));

        assertEquals(1, cityManager.getCitiesWithConflict().size());

        List<City> expectedCities = new ArrayList<>();
        expectedCities.add(new City("A"));

        assertEquals(expectedCities, cityManager.getCitiesWithConflict());
    }
}
