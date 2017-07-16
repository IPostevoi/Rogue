package dungeon;

/**
 * Created by bakla410 on 14.07.17.
 */
public class Site {
    private int i;
    private int j;
    private SiteType siteType;

    public Site(int i, int j, SiteType siteType) {
        this.i = i;
        this.j = j;
        this.siteType = siteType;
    }

    public SiteType getSiteType() {
        return siteType;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public double manhattanTo(Site site) {
        double dx = Math.abs(site.getI() - i);
        double dy = Math.abs(site.getJ() - j);
        return dx + dy;
    }
}
