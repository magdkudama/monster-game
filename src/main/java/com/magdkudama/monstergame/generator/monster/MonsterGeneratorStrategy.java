package com.magdkudama.monstergame.generator.monster;

import com.magdkudama.monstergame.model.World;

public interface MonsterGeneratorStrategy {
    public Integer getNextPositionFor(World world);
}
