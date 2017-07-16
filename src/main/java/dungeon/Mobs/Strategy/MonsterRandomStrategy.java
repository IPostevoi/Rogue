package dungeon.Mobs.Strategy;

import dungeon.Dungeon;
import dungeon.Mobs.Monster;
import dungeon.Mobs.Rogue;
import dungeon.Site;

import java.util.ArrayList;

/**
 * Created by bakla410 on 14.07.17.
 */
public class MonsterRandomStrategy implements Strategy {
    private Dungeon dungeon;

    public MonsterRandomStrategy(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    public void move() {
        Site currentSite = dungeon.getMonster().getSite();
        ArrayList<Site> neighbours =  dungeon.getNeighbours(currentSite);
        int rndIndex = (int) (Math.random() * neighbours.size());
        while (!dungeon.isMoveLegal(currentSite, neighbours.get(rndIndex))) {
            neighbours.remove(rndIndex);
            rndIndex = (int) (Math.random() * neighbours.size());
        }
        dungeon.getMonster().move(neighbours.get(rndIndex));
    }
}

