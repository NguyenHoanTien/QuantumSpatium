package Entity;

import Audio.Music;
import Entity.mob.Abilityshoot;
import Entity.mob.Chaser;
import Entity.mob.Dummy;
import Entity.mob.Star;
import Entity.spawner.ParticleSpawner;
import Graphics.Screen;
import Graphics.Sprite;
import game.Game;
import java.util.Random;
import level.Level;

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

    public void damageHealth(int damage) {
        health -= damage;
        if (health <= 0) {
            Music.exp.play();
            removed = true;
            level.decrement_level_display();
            count++;

            // check enable ability
            if (level.enableAbility) {
                Random rand = new Random();
                int per = rand.nextInt(99);
                if (per <= level.abiPercent) {
                    level.add(new Abilityshoot(((int) x / 16), ((int) y / 16)));
                }
            }

            level.add(new ParticleSpawner((int) x, (int) y, 44, 15, level, Sprite.particle_gray));
            if (this instanceof Dummy) {
                level.add(new ParticleSpawner((int) x, (int) y, 44, 15, level, Sprite.particle_blue));
                level.score += 10;
            } else if (this instanceof Chaser) {
                level.add(new ParticleSpawner((int) x, (int) y, 44, 15, level, Sprite.particle_green));
                level.score += 30;
            } else if (this instanceof Star) {
                level.add(new ParticleSpawner((int) x, (int) y, 44, 15, level, Sprite.particle_yellow));
                level.score += 20;
            }
            if (level.get_level_num() > 0) {
                level.spawnMob();

            }
        }
    }
}
