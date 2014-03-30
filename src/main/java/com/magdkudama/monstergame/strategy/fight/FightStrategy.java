package com.magdkudama.monstergame.strategy.fight;

import com.magdkudama.monstergame.model.Monster;

import java.util.List;

public interface FightStrategy {
    public Monster chooseWinnerMonster(List<Monster> monsters);
}
