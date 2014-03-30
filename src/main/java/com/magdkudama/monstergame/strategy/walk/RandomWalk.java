package com.magdkudama.monstergame.strategy.walk;

import com.magdkudama.monstergame.model.City;
import com.magdkudama.monstergame.model.Monster;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomWalk implements WalkStrategy {
    @Override
    public City chooseCityForMonster(Monster monster) {
        City currentCity = monster.getCurrentCity();
        List<City> validNeighbors = new ArrayList<>();
        for (City city : currentCity.getNeighbors().values()) {
            if (!city.isDestroyed()) {
                validNeighbors.add(city);
            }
        }

        Integer maxRandom = validNeighbors.size() - 1;
        City newCity = null;

        if (maxRandom.equals(0)) {
            newCity = validNeighbors.get(0);
        } else if (maxRandom > 0) {
            Random randomGenerator = new Random();
            Integer position = randomGenerator.nextInt(validNeighbors.size() - 1);
            newCity = validNeighbors.get(position);
        }

        return newCity;
    }
}
