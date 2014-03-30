Monster game
============

This is a simple implementation of the monster game using Java & Maven. A simple implementation could be:

```java
import com.magdkudama.monstergame.converter.decoder.CsvDecoder;
import com.magdkudama.monstergame.converter.encoder.CsvEncoder;
import com.magdkudama.monstergame.generator.Generator;
import com.magdkudama.monstergame.generator.WorldGenerator;
import com.magdkudama.monstergame.generator.monster.RandomStrategy;
import com.magdkudama.monstergame.manager.GameManager;
import com.magdkudama.monstergame.model.FightResult;
import com.magdkudama.monstergame.model.Monster;
import com.magdkudama.monstergame.strategy.fight.FightStrategy;
import com.magdkudama.monstergame.strategy.fight.RandomWinner;
import com.magdkudama.monstergame.strategy.walk.RandomWalk;
import com.magdkudama.monstergame.strategy.walk.WalkStrategy;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            Generator generator = new WorldGenerator(
                    new CsvDecoder(new FileReader("/path/to/map")), // Path to map descriptor
                    new RandomStrategy(),                           // Strategy used to place monsters
                    10                                              // Number of monsters
            );

            GameManager manager = new GameManager(
                    generator.generate(),                             // Generated, parsed and validated world
                    new CsvEncoder(new FileWriter("/path/to/result")) // Path to resultant file
            );

            
            // Strategies used to walk between cities and fight (monster who wins)
            WalkStrategy walkStrategy = new RandomWalk();
            FightStrategy fightStrategy = new RandomWinner();
            
            // Here 1000 is the maximum number of movements
            for (Integer i = 0; i < 1000; i++) {
                // Start the party!
                manager.walkToNeighborCityWithStrategy(walkStrategy);
                List<FightResult> results = manager.dealWithFights(fightStrategy);

                for (FightResult fight : results) {
                    String city = "Destroyed city " + fight.getCityDestroyed().getName();
                    String monsters = "Monsters involved: ";
                    for (Monster monster : fight.getMonstersInvolved()) {
                        monsters += monster.getId() + " ";
                    }

                    System.out.println(city);
                    System.out.println(monsters);
                }

                if (manager.areAllCitiesDestroyed()) {
                    System.out.println("All cities destroyed... Nothing to do here!");
                    return;
                }
            }

            System.out.println("Game has finished, but there are still cities 'alive'. Encoding again the file!");
            manager.encodeRemainingCities();

        } catch (IOException ex) {
            System.out.println("Do you know what's happening? Neither do I!");
        }
    }
}
