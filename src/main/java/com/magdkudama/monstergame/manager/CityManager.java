package com.magdkudama.monstergame.manager;

import com.magdkudama.monstergame.model.City;

import java.util.ArrayList;
import java.util.List;

public class CityManager {
    private List<City> cities;

    public CityManager(List<City> cities) {
        this.cities = cities;
    }

    public List<City> getCitiesWithMonsters() {
        List<City> citiesWithMonsters = new ArrayList<>();

        for (City city : cities) {
            if (city.getMonsters().size() > 0) {
                citiesWithMonsters.add(city);
            }
        }

        return citiesWithMonsters;
    }

    public List<City> getRemainingCities() {
        List<City> remainingCities = new ArrayList<>();

        for (City city : cities) {
            if (!city.isDestroyed()) {
                remainingCities.add(city);
            }
        }

        return remainingCities;
    }

    public List<City> getCitiesWithConflict() {
        List<City> conflictCities = new ArrayList<>();

        for (City city : getRemainingCities()) {
            if (city.getMonsters().size() > 1) {
                conflictCities.add(city);
            }
        }

        return conflictCities;
    }

    public List<City> getCities() {
        return cities;
    }
}
