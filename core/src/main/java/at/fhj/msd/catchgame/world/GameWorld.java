package at.fhj.msd.catchgame.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import at.fhj.msd.catchgame.entities.FallingObject;
import at.fhj.msd.catchgame.entities.Player;
import at.fhj.msd.catchgame.entities.Projectile;

public class GameWorld {
    private Player player;
    private List<FallingObject> fallingObjects = new ArrayList<>();
    private List<Projectile> projectiles = new ArrayList<>();
    private int score = 0;
    private int level = 1;
    private boolean gameOver = false;
    private float spawnTimer = 0;
    private float spawnInterval = 1.15f;
    private float objectSpeed = 80f;
    private Random random = new Random();
    private boolean projectileHit = false;
    private boolean paused = false;

    public GameWorld() {
        player = new Player(295, 0);
    }

    public void update(float delta) {
        if (gameOver || paused) return;

        // Level-Up Logik
        if (score >= 4000 && level == 2) {
            level = 3;
            spawnInterval = 0.44f;
            objectSpeed = 220f;
            System.out.println("LEVEL UP! Level 3 gestartet. Maximale Geschwindigkeit!");
        } else if (score >= 1000 && level == 1) {
            level = 2;
            spawnInterval = 0.88f;
            objectSpeed = 140f;
            System.out.println("LEVEL UP! Level 2 gestartet. Alles wird schneller!");
        }

        spawnTimer += delta;
        if (spawnTimer >= spawnInterval) {
            // 1. Erstes Objekt spawnen
            int spawnX1 = 260 + random.nextInt(120);
            fallingObjects.add(new FallingObject(spawnX1, 480, random.nextBoolean() ? FallingObject.Type.MONSTER : FallingObject.Type.HUMAN, objectSpeed));

            // 2. 25% Chance auf ein zweites Objekt
            if (random.nextFloat() < 0.25f) {
                int spawnX2 = 260 + random.nextInt(120);
                if (Math.abs(spawnX1 - spawnX2) < 40) {
                    spawnX2 = (spawnX2 + 60 > 470) ? spawnX2 - 60 : spawnX2 + 60;
                }
                fallingObjects.add(new FallingObject(spawnX2, 480, random.nextBoolean() ? FallingObject.Type.MONSTER : FallingObject.Type.HUMAN, objectSpeed));
            }

            spawnTimer = 0;
        }

        projectiles.removeIf(p -> { p.update(delta); return p.getY() > 480; });

        for (int i = fallingObjects.size() - 1; i >= 0; i--) {
            FallingObject obj = fallingObjects.get(i);
            obj.update(delta);

            if (obj.getY() < 0) {
                if (obj.getType() == FallingObject.Type.MONSTER) gameOver = true;
                else score += 50;
                fallingObjects.remove(i);
                continue;
            }

            for (int j = projectiles.size() - 1; j >= 0; j--) {
                Projectile p = projectiles.get(j);
                if (p.getX() < obj.getX() + 40 && p.getX() + 10 > obj.getX() &&
    p.getY() < obj.getY() + 40 && p.getY() + 20 > obj.getY()) {

    projectileHit = true;

    score += obj.getType() == FallingObject.Type.MONSTER ? 100 : -200;

    fallingObjects.remove(i);
    projectiles.remove(j);

    break;
}
            }
        }
    }

    public void shoot() {
    if (!gameOver && !paused) projectiles.add(new Projectile(player.getX() + 20, player.getY() + 50));
    }

    public Player getPlayer() { return player; }
    public List<FallingObject> getFallingObjects() { return fallingObjects; }
    public List<Projectile> getProjectiles() { return projectiles; }
    public boolean isGameOver() { return gameOver; }
    public int getScore() { return score; }
    public int getLevel() { return level; }
    public void togglePause() { paused = !paused; }
    public boolean isPaused() { return paused; }
    public boolean consumeProjectileHit() {

    if (projectileHit) {

        projectileHit = false;

        return true;
    }

    return false;
}
}
