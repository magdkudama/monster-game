package com.magdkudama.monstergame.strategy.walk;

import com.magdkudama.monstergame.model.City;
import com.magdkudama.monstergame.model.Monster;

public interface WalkStrategy {
    public City chooseCityForMonster(Monster monster);
}
