package com.magdkudama.monstergame.model;

import java.util.List;

public class FightResult {
    private City cityDestroyed;
    private List<Monster> monstersInvolved;
    private Monster winner;

    public City getCityDestroyed() {
        return cityDestroyed;
    }

    public void setCityDestroyed(City cityDestroyed) {
        this.cityDestroyed = cityDestroyed;
    }

    public List<Monster> getMonstersInvolved() {
        return monstersInvolved;
    }

    public void setMonstersInvolved(List<Monster> monstersInvolved) {
        this.monstersInvolved = monstersInvolved;
    }

    public Monster getWinner() {
        return winner;
    }

    public void setWinner(Monster winner) {
        this.winner = winner;
    }
}
