package com.magdkudama.monstergame.model;

public class Monster {
    private Integer id;
    private City currentCity;
    private Boolean died = false;

    public Monster(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public City getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(City currentCity) {
        this.currentCity = currentCity;
    }

    public void kill() {
        died = true;
    }

    public Boolean isDied() {
        return died;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Monster)) return false;

        Monster monster = (Monster) o;

        if (!died.equals(monster.died)) return false;
        if (!id.equals(monster.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + died.hashCode();
        return result;
    }
}
