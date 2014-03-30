package com.magdkudama.monstergame.strategy.fight;

import com.magdkudama.monstergame.model.Monster;
import com.magdkudama.monstergame.strategy.fight.exception.NoMonstersException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class TestRandomWinner {
    private List<Monster> monstersList;
    private FightStrategy strategy;

    @Before
    public void setUp() {
        monstersList = new ArrayList<>();
        strategy = new RandomWinner();
    }

    @Test
    public void testWinnerIsAlwaysInList() {
        monstersList.add(new Monster(1));
        monstersList.add(new Monster(2));
        monstersList.add(new Monster(3));
        monstersList.add(new Monster(4));

        Monster winner = strategy.chooseWinnerMonster(monstersList);
        assertTrue(monstersList.contains(winner));
    }

    @Test
    public void testWinnerIsTheFirstOneWhenThereIsOnlyOneElement() {
        Monster monster = new Monster(1);
        monstersList.add(monster);

        Monster winner = strategy.chooseWinnerMonster(monstersList);
        assertSame(monster, winner);
    }

    @Test(expected = NoMonstersException.class)
    public void testEmptyListThrowsException() {
        strategy.chooseWinnerMonster(monstersList);
    }
}
