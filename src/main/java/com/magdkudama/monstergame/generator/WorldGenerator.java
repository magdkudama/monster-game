package com.magdkudama.monstergame.generator;

import com.magdkudama.monstergame.converter.Decoder;
import com.magdkudama.monstergame.generator.monster.MonsterGeneratorStrategy;
import com.magdkudama.monstergame.model.City;
import com.magdkudama.monstergame.model.Monster;
import com.magdkudama.monstergame.model.World;

import java.io.IOException;

public class WorldGenerator implements Generator {
    private Decoder decoder;
    private MonsterGeneratorStrategy strategy;
    private Integer numberOfMonsters;

    public WorldGenerator(Decoder decoder, MonsterGeneratorStrategy monsterStrategyGenerator, Integer numberOfMonsters) {
        this.decoder = decoder;
        this.strategy = monsterStrategyGenerator;
        this.numberOfMonsters = numberOfMonsters;
    }

    public World generate() throws IOException {
        World world = decoder.decode();
        for (Integer monsterId = 1; monsterId <= numberOfMonsters; monsterId++) {
            Integer position = strategy.getNextPositionFor(world);
            City city = world.getCities().get(position);
            Monster monster = new Monster(monsterId);
            monster.setCurrentCity(city);
            city.addMonster(monster);
        }

        return world;
    }
}
