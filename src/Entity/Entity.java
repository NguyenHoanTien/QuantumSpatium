package Entity;

import Entity.mob.Chaser;
import Entity.mob.Dummy;
import Entity.mob.Star;
import Entity.spawner.ParticleSpawner;
import Graphics.Screen;
import Graphics.Sprite;
import java.util.Random;
import level.Level;
import level.respawnMob;

public class Entity {

    protected int x, y;
    private boolean removed = false;
    protected Level level;
    protected final Random random = new Random();
    protected Sprite sprite;
    protected int health;

    public Entity() {

    }

    public Entity(int x, int y, Sprite sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }

    public void update() {

    }

    public void render(Screen screen) {
        if (sprite != null) {
            screen.renderSprite((int) x, (int) y, sprite, true);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void remove() { // remove from level
        removed = true;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void init(Level level) {
        this.level = level;

    }

    public int getHealth() {
        return health;
    }

    int count = 0;
    private respawnMob respawn;

    public void damageHealth(int damage) {
        health -= damage;
        if (health <= 0) {
            removed = true;
            count++;
            //System.out.println("Count: " + count);
            level.add(new ParticleSpawner((int) x, (int) y, 44, 15, level, Sprite.particle_gray));

            if (this instanceof Dummy) {
                level.add(new ParticleSpawner((int) x, (int) y, 44, 15, level, Sprite.particle_blue));
            } else if (this instanceof Chaser) {
                level.add(new ParticleSpawner((int) x, (int) y, 44, 15, level, Sprite.particle_green));
            } else if (this instanceof Star) {
                level.add(new ParticleSpawner((int) x, (int) y, 44, 15, level, Sprite.particle_yellow));
            }
            if (respawn.get_level_num() > 0) {
                respawn.spawnMob();

            }
        }
    }
}