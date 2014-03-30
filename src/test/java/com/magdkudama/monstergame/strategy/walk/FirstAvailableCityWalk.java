package com.magdkudama.monstergame.strategy.walk;

import com.magdkudama.monstergame.model.City;
import com.magdkudama.monstergame.model.Monster;
import com.magdkudama.monstergame.model.neighbor.NeighborLocation;

import java.util.Map;

public class FirstAvailableCityWalk implements WalkStrategy {
    @Override
    public City chooseCityForMonster(Monster monster) {
        Map.Entry<NeighborLocation, City> firstEntry = monster.getCurrentCity().getNeighbors().entrySet().iterator().next();
        return firstEntry.getValue();
    }
}
