package com.magdkudama.monstergame.strategy.fight;

import com.magdkudama.monstergame.model.Monster;
import com.magdkudama.monstergame.strategy.fight.exception.NoMonstersException;

import java.util.List;
import java.util.Random;

public class RandomWinner implements FightStrategy {
    @Override
    public Monster chooseWinnerMonster(List<Monster> monsters) {
        Integer maxRandom = monsters.size() - 1;

        Monster monster;
        switch (maxRandom) {
            case -1:
                throw new NoMonstersException();
            case 0:
                monster = monsters.get(0);
                break;
            default:
                Random randomGenerator = new Random();
                Integer position = randomGenerator.nextInt(monsters.size() - 1);
                monster = monsters.get(position);
        }

        return monster;
    }
}
