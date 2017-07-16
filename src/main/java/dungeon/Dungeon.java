package dungeon;

import dungeon.Mobs.Monster;
import dungeon.Mobs.Rogue;

import java.util.ArrayList;

/**
 * Created by bakla410 on 14.07.17.
 */
public class Dungeon {
    private Site[][] board;
    private Rogue rogue;
    private Monster monster;
    private int N;
    private int M;

    public Dungeon(int N, int M) {
        this.N = N;
        this.M = M;
        board = new Site[N][M];
    }

    public void setSite(int i, int j, SiteType type) {
        board[i][j] = new Site(i, j, type);
    }

    public Site getSite(int i, int j) {
        if ((i >= 0 && i < N) && (j >= 0 && j < M)) {
            return board[i][j];
        } else {
            return null;
        }
    }

    public void setRogue(Rogue rogue) {
        this.rogue = rogue;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public Rogue getRogue() {
        return rogue;
    }

    public Monster getMonster() {
        return monster;
    }

    public int getHeight() {
        return N;
    }

    public int getLength() {
        return M;
    }

    public boolean isMoveLegal(Site oldSite, Site newSite) {
        if (Math.abs(oldSite.getI() - newSite.getI()) > 1 ||
                (Math.abs(oldSite.getJ() - newSite.getJ()) > 1) ||
                newSite.getSiteType() == SiteType.Wall) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<Site> getNeighbours(Site site) {
        int currentI = site.getI();
        int currentJ = site.getJ();
        Site NW = getSite(currentI - 1, currentJ - 1);
        Site N = getSite(currentI - 1, currentJ);
        Site NE = getSite(currentI - 1, currentJ + 1);
        Site W = getSite(currentI, currentJ - 1);
        Site E = getSite(currentI, currentJ + 1);
        Site SW = getSite(currentI + 1, currentJ - 1);
        Site S = getSite(currentI + 1, currentJ);
        Site SE = getSite(currentI + 1, currentJ + 1);
        Site[] neighbours = {NW, N, NE, W, E, SW, S, SE};
        ArrayList<Site> validNeighbours = new ArrayList<Site>();
        for (Site validNeighbour: neighbours) {
            if (validNeighbour != null) {
                validNeighbours.add(validNeighbour);
            }
        }
        return validNeighbours;
    }

    public boolean isCaught() {
        return rogue.getSite().equals(monster.getSite());
    }
}
