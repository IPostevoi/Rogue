package dungeon.Mobs;

import dungeon.Site;

/**
 * Created by bakla410 on 16.07.17.
 */
public class Mob {
    private Site site;

    public Mob(Site site) {
        this.site = site;
    }

    public Site getSite() {
        return site;
    }

    public void move(Site site) {
        this.site = site;
    }
}
