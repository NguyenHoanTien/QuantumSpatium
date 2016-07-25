package Entity.projectile;

import Entity.spawner.Spawner;
import Entity.partical.Particle;
import Entity.spawner.ParticleSpawner;
import Graphics.Screen;
import Graphics.Sprite;

public class WizardProjectile extends Projectile {

    public static final int FireRate = 15;      // Higher is slower!

    public WizardProjectile(int x, int y, double dir) {
        super(x, y, dir);
        range = random.nextInt(100) + 300;    
        damage = 20;
        speed = 6;
        sprite = Sprite.shoot;
        nx = speed * Math.cos(angle);
        ny = speed * Math.sin(angle);
    }

    public void update() {
        move();
        if (level.tileCollision((int)(x + nx), (int)(y + ny), 5, 6, 5)) {
            // 44 = time life, 50 = number of particle
            level.add(new ParticleSpawner((int)x,(int)y, 44, 50, level));       
            remove();
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
        screen.renderProjectile((int) x - 8, (int) y - 18, this);   // bullet shooting potition
    }

}
