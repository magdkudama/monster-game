package com.magdkudama.monstergame.manager;

import com.magdkudama.monstergame.converter.Encoder;
import com.magdkudama.monstergame.model.City;
import com.magdkudama.monstergame.model.FightResult;
import com.magdkudama.monstergame.model.Monster;
import com.magdkudama.monstergame.model.World;
import com.magdkudama.monstergame.strategy.fight.FightStrategy;
import com.magdkudama.monstergame.strategy.walk.WalkStrategy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameManager {
    private Encoder encoder;
    private CityManager cityManager;

    public GameManager(World world, Encoder encoder) {
        this.encoder = encoder;
        cityManager = new CityManager(world.getCities());
    }

    public void walkToNeighborCityWithStrategy(WalkStrategy strategy) {
        Map<Monster, City> bulkActions = new HashMap<>();

        for (City city : cityManager.getRemainingCities()) {
            for (Monster monster : city.getMonsters()) {
                City newCity = strategy.chooseCityForMonster(monster);
                if (newCity != null) {
                    bulkActions.put(monster, newCity);
                }
            }
        }

        for (Map.Entry<Monster, City> entry : bulkActions.entrySet()) {
            Monster monster = entry.getKey();
            City city = entry.getValue();
            monster.getCurrentCity().removeMonster(monster);
            monster.setCurrentCity(city);
            city.addMonster(monster);
        }
    }

    public List<FightResult> dealWithFights(FightStrategy strategy) {
        List<FightResult> fightResults = new ArrayList<>();

        for (City city : cityManager.getCitiesWithConflict()) {
            Monster winningMonster = strategy.chooseWinnerMonster(city.getMonsters());

            FightResult fightResult = new FightResult();
            fightResult.setCityDestroyed(city);
            fightResult.setMonstersInvolved(city.getMonsters());
            fightResult.setWinner(winningMonster);
            fightResults.add(fightResult);

            city.destroy();

            for (Monster monster : city.getMonsters()) {
                if (!monster.equals(winningMonster)) {
                    monster.kill();
                }
            }
        }

        return fightResults;
    }

    public Boolean areAllCitiesDestroyed() {
        return cityManager.getRemainingCities().size() == 0;
    }

    public Integer numberOfRemainingCities() {
        return cityManager.getRemainingCities().size();
    }

    public void encodeRemainingCities() throws IOException {
        encoder.encode(cityManager.getRemainingCities());
    }

    public CityManager getCityManager() {
        return cityManager;
    }
}
