package com.magdkudama.monstergame.model;

import com.magdkudama.monstergame.model.neighbor.NeighborLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class City {
    private String name;
    private Boolean destroyed = false;
    private List<Monster> monsters = new ArrayList<>();
    private Map<NeighborLocation, City> neighbors;

    public City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Boolean isDestroyed() {
        return destroyed;
    }

    public void destroy() {
        this.destroyed = true;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public void addMonster(Monster monster) {
        monsters.add(monster);
    }

    public void removeMonster(Monster monster) {
        getMonsters().remove(monster);
    }

    public Map<NeighborLocation, City> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(Map<NeighborLocation, City> neighbors) {
        this.neighbors = neighbors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;

        City city = (City) o;

        if (!destroyed.equals(city.destroyed)) return false;
        if (!name.equals(city.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + destroyed.hashCode();
        return result;
    }
}
