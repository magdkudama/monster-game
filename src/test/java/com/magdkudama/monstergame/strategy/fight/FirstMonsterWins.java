package com.magdkudama.monstergame.strategy.fight;

import com.magdkudama.monstergame.model.Monster;

import java.util.List;

public class FirstMonsterWins implements FightStrategy {
    @Override
    public Monster chooseWinnerMonster(List<Monster> monsters) {
        return monsters.get(0);
    }
}
