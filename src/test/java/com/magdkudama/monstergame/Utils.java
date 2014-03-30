package com.magdkudama.monstergame;

import com.magdkudama.monstergame.model.City;
import com.magdkudama.monstergame.model.Monster;
import com.magdkudama.monstergame.model.World;
import com.magdkudama.monstergame.model.neighbor.NeighborLocation;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Utils {
    public static String testsDirectory() {
        String testFolder = System.getProperty("user.dir");
        return testFolder + "/src/test/java/com/magdkudama/monstergame/";
    }

    public static World getTestWorld() {
        World expectedWorld = new World();

        List<City> cities = new ArrayList<>();
        City a = new City("A");
        City b = new City("B");
        City c = new City("C");
        City d = new City("D");

        Map<NeighborLocation, City> aNeighbors = new LinkedHashMap<>();
        aNeighbors.put(NeighborLocation.NORTH, b);
        aNeighbors.put(NeighborLocation.EAST, c);
        a.setNeighbors(aNeighbors);

        Monster monster1 = new Monster(1);
        monster1.setCurrentCity(a);
        a.addMonster(monster1);
        Monster monster2 = new Monster(2);
        monster2.setCurrentCity(a);
        a.addMonster(monster2);

        Map<NeighborLocation, City> bNeighbors = new LinkedHashMap<>();
        bNeighbors.put(NeighborLocation.SOUTH, a);
        b.setNeighbors(bNeighbors);

        Map<NeighborLocation, City> cNeighbors = new LinkedHashMap<>();
        cNeighbors.put(NeighborLocation.WEST, a);
        c.setNeighbors(cNeighbors);

        Map<NeighborLocation, City> dNeighbors = new LinkedHashMap<>();
        d.setNeighbors(dNeighbors);

        cities.add(a);
        cities.add(b);
        cities.add(c);
        cities.add(d);

        expectedWorld.setCities(cities);

        return expectedWorld;
    }
}
