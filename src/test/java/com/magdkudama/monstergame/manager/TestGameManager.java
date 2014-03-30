package com.magdkudama.monstergame.manager;

import com.magdkudama.monstergame.Utils;
import com.magdkudama.monstergame.converter.Encoder;
import com.magdkudama.monstergame.model.City;
import com.magdkudama.monstergame.model.FightResult;
import com.magdkudama.monstergame.model.Monster;
import com.magdkudama.monstergame.model.World;
import com.magdkudama.monstergame.strategy.fight.FightStrategy;
import com.magdkudama.monstergame.strategy.fight.FirstMonsterWins;
import com.magdkudama.monstergame.strategy.walk.FirstAvailableCityWalk;
import com.magdkudama.monstergame.strategy.walk.WalkStrategy;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class TestGameManager {
    private GameManager gameManager;

    @Before
    public void setUp() {
        World world = Utils.getTestWorld();
        Encoder mockedEncoder = mock(Encoder.class);
        gameManager = new GameManager(world, mockedEncoder);
    }

    @Test
    public void testWalkingToNeighborCityWorks() {
        WalkStrategy strategy = new FirstAvailableCityWalk();
        gameManager.walkToNeighborCityWithStrategy(strategy);

        assertEquals(1, gameManager.getCityManager().getCitiesWithMonsters().size());
    }

    @Test
    public void testDealingWithFightsWorks() {
        FightStrategy strategy = new FirstMonsterWins();
        List<FightResult> results = gameManager.dealWithFights(strategy);

        assertEquals(1, results.size());

        for (FightResult fightResult : results) {
            assertEquals(new Monster(1), fightResult.getWinner());
        }

        for (FightResult fightResult : results) {
            for (Monster monster : fightResult.getMonstersInvolved()) {
                if (!fightResult.getWinner().equals(monster)) {
                    assertTrue(monster.isDied());
                }
            }
        }
    }

    @Test
    public void testDestroyedCitiesFiltersCorrectly() {
        Integer numberOfCities = gameManager.getCityManager().getCities().size();
        for (City city : gameManager.getCityManager().getCities()) {
            city.destroy();
            numberOfCities -= 1;
            if (numberOfCities.equals(0)) {
                assertTrue(gameManager.areAllCitiesDestroyed());
            } else {
                assertFalse(gameManager.areAllCitiesDestroyed());
            }

            assertEquals(numberOfCities, gameManager.numberOfRemainingCities());
        }
    }
}
