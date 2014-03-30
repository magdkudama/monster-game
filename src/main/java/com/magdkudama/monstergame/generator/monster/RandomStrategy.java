package com.magdkudama.monstergame.generator.monster;

import com.magdkudama.monstergame.model.World;

import java.util.Random;

public class RandomStrategy implements MonsterGeneratorStrategy {
    @Override
    public Integer getNextPositionFor(World world) {
        Random randomGenerator = new Random();
        Integer position = randomGenerator.nextInt(world.getCities().size() - 1);

        return position;
    }
}
