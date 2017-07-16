package dungeon;

import dungeon.Mobs.Mob;
import dungeon.Mobs.Monster;
import dungeon.Mobs.Rogue;
import dungeon.Mobs.Strategy.MonsterRandomStrategy;
import dungeon.Mobs.Strategy.RogueRandomStrategy;
import dungeon.Mobs.Strategy.Strategy;
import edu.princeton.cs.algs4.StdDraw;

import java.io.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by bakla410 on 14.07.17.
 */
public class Game {
    private Dungeon dungeon;
    private Strategy rogueStrategy;
    private Strategy monsterStrategy;
    private int N;
    private int M;

    public Game(String filename) throws IOException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String NM = br.readLine();
            N = Integer.parseInt(NM.split("x")[0]);
            M = Integer.parseInt(NM.split("x")[1]);
            dungeon = new Dungeon(N, M);
            String line = br.readLine();
            String[] parsedLine;
            for (int i = 0; i < N; i++) {
                parsedLine = line.split(" ");
                for (int j = M - 1; j >= 0; j--) {
                    if (parsedLine[j].equals(".")) {
                        dungeon.setSite(i, j, SiteType.Room);
                    } else if (parsedLine[j].equals("-")) {
                        dungeon.setSite(i, j, SiteType.Wall);
                    }
                }
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: file not found");
        }
    }

    private void spawnRogue(int i, int j) {
        dungeon.setRogue(new Rogue(dungeon.getSite(i, j)));
    }

    private void spawnMonster(int i, int j) {
       dungeon.setMonster(new Monster(dungeon.getSite(i, j)));
    }

    private void setRogueRandomStrategy() {
        rogueStrategy = new RogueRandomStrategy(dungeon);
    }

    private void setRandomMonsterStrartegy() {
        monsterStrategy = new MonsterRandomStrategy(dungeon);
    }
    public void drawBoard() {
        for (int i = 0; i < N; i++) {
            StdDraw.line(0, i, N, i);
        }
        for (int j = 0; j < M; j++) {
            StdDraw.line(j, 0, j, M);
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                switch (dungeon.getSite(i, j).getSiteType()) {
                    case Wall: StdDraw.filledSquare(j + 0.5, i + 0.5, 0.5);
                        break;
                }
            }
        }
        StdDraw.setPenColor(0, 0, 255);
        drawMob(dungeon.getRogue());
        StdDraw.setPenColor(255, 0, 0);
        drawMob(dungeon.getMonster());
        StdDraw.setPenColor();
        StdDraw.show();
        StdDraw.pause(1);
    }

    private void drawMob(Mob mob) {
        int y = mob.getSite().getI();
        int x = mob.getSite().getJ();
        StdDraw.filledCircle(x + 0.5,y + 0.5, 0.25);
    }

    public void start() throws InterruptedException {
        spawnRogue(1, 0);
        spawnMonster(2, 1);
        setRogueRandomStrategy();
        setRandomMonsterStrartegy();
        StdDraw.setXscale(0, M);
        StdDraw.setYscale(N, 0);
        StdDraw.enableDoubleBuffering();
        drawBoard();
        for (int t = 0; t < 100; t++) {
            TimeUnit.SECONDS.sleep(1);
            rogueStrategy.move();
            StdDraw.clear();
            drawBoard();
            if (dungeon.isCaught()) {
                break;
            }
            TimeUnit.SECONDS.sleep(1);
            monsterStrategy.move();
            StdDraw.clear();
            drawBoard();
            if (dungeon.isCaught()) {
                break;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        try {
            Game game = new Game("src/main/resources/dungeon.txt");
            game.start();
            System.out.println("Game over");
        } catch (IOException e) {
            System.out.println("Couldn't read the file!");
        }
    }
}
