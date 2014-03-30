package com.magdkudama.monstergame.strategy.walk;

import com.magdkudama.monstergame.model.City;
import com.magdkudama.monstergame.model.Monster;
import com.magdkudama.monstergame.model.neighbor.NeighborLocation;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class TestRandomWalk {
    private WalkStrategy strategy;
    private Map<NeighborLocation, City> neighbors;
    private City city;
    private Monster monster;

    @Before
    public void setUp() {
        strategy = new RandomWalk();
        city = new City("Fake City");
        neighbors = new HashMap<>();
        monster = new Monster(1);
    }

    @Test
    public void testNewCityIsAlwaysInList() {
        addNorthCity();
        addSouthCity();
        addEastCity();
        addWestCity();
        city.setNeighbors(neighbors);
        monster.setCurrentCity(city);

        City newCity = strategy.chooseCityForMonster(monster);
        assertTrue(monster.getCurrentCity().getNeighbors().containsValue(newCity));
    }

    @Test
    public void testNewCityIsTheFirstOneWhenThereIsOnlyOneElement() {
        City northCity = addNorthCity();
        city.setNeighbors(neighbors);
        monster.setCurrentCity(city);

        City newCity = strategy.chooseCityForMonster(monster);
        assertSame(northCity, newCity);
    }

    @Test
    public void testEmptyListThrowsException() {
        city.setNeighbors(neighbors);
        monster.setCurrentCity(city);

        City newCity = strategy.chooseCityForMonster(monster);
        assertNull(newCity);
    }

    protected City addNorthCity() {
        City northCity = new City("North");
        neighbors.put(NeighborLocation.NORTH, northCity);

        return northCity;
    }

    protected City addSouthCity() {
        City southCity = new City("South");
        neighbors.put(NeighborLocation.SOUTH, southCity);

        return southCity;
    }

    protected City addEastCity() {
        City eastCity = new City("East");
        neighbors.put(NeighborLocation.EAST, eastCity);

        return eastCity;
    }

    protected City addWestCity() {
        City westCity = new City("West");
        neighbors.put(NeighborLocation.WEST, westCity);

        return westCity;
    }
}
