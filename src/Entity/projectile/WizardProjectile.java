package Entity.projectile;

import Entity.Entity;
import Entity.mob.Chaser;
import Entity.mob.Dummy;
import Entity.mob.Star;
import Entity.spawner.ParticleSpawner;
import Graphics.Screen;
import Graphics.Sprite;
import java.util.List;

public class WizardProjectile extends Projectile {

    public static final int FireRate = 15;      // Higher is slower! Minimum is 1

    public WizardProjectile(double x, double y, double dir) {
        super(x, y, dir);
        range = random.nextInt(100) + 350;
        damage = 1;
        bulletspeed = 8;
        sprite = Sprite.shoot;
        nx = bulletspeed * Math.cos(angle);
        ny = bulletspeed * Math.sin(angle);
    }

    public void update() {
        move();
        List<Entity> entities = level.entities;
        updateMobCollision(entities);

        if (level.tileCollision((int) (x + nx), (int) (y + ny), 5, 6, 5)) {
            // 44 = time life, 50 = number of particle
            level.add(new ParticleSpawner((int) x, (int) y, 44, 50, level));
            this.remove();
        }
    }

    public void updateMobCollision(List<Entity> entities) {

        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i) instanceof Dummy
                    || entities.get(i) instanceof Chaser
                    || entities.get(i) instanceof Star) {
                if (x < entities.get(i).getX() + 15
                        && x > entities.get(i).getX() - 15
                        && y < entities.get(i).getY() + 15
                        && y > entities.get(i).getY() - 15) {
                    remove();

                    //heal -= damage;
                    
                    entities.get(i).damageHealth(damage);
                  
                    //System.out.println(entities.get(i) + " Died");
                    //level.add(new ParticleSpawner((int) x, (int) y, 44, 50, level));

                    remove();
                }
            }
        }
    }

    protected void move() {
        x += nx;
        y += ny;
        if (distance() > range) {
            remove();
        }
    }

    private double distance() {
        double dist = 0;
        dist = Math.sqrt(Math.abs((xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y)));
        return dist;
    }

    public void render(Screen screen) {
        screen.renderProjectile((int) x - 8, (int) y - 7, this);   // bullet shooting potition
    }

}
