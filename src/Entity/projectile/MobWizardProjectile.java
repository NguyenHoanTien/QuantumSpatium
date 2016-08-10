
package Entity.projectile;

import Entity.Entity;
import Entity.mob.Chaser;
import Entity.mob.Dummy;
import Entity.mob.Player;
import Entity.mob.Star;
import Entity.spawner.ParticleSpawner;
import Graphics.Screen;
import Graphics.Sprite;
import java.util.List;

public class MobWizardProjectile extends Projectile {
    public static int FireRate = 35;      // Higher is slower! Minimum is 1
    public static int FireRateD = 30;
    
    public MobWizardProjectile(double x, double y, double dir, int speed) {
        super(x, y, dir);
        range = random.nextInt(100) + 150;
        //damage = 20;
        sprite = Sprite.shoot;
        nx = speed * Math.cos(angle);
        ny = speed * Math.sin(angle);
    }

    public void update() {
        move();
        List<Player> player = level.players;
        updateCollision(player);

        if (level.tileCollision((int) (x + nx), (int) (y + ny), 5, 6, 5)) {
            // 44 = time life, 50 = number of particle
            level.add(new ParticleSpawner((int) x, (int) y, 44, 50, level));
            this.remove();
        }
    }

    public void updateCollision(List<Player> players) {
        
        for (int i = 0; i < players.size(); i++) {
                if (x < players.get(i).getX() + 20
                        && x > players.get(i).getX() - 20
                        && y < players.get(i).getY() + 20
                        && y > players.get(i).getY() - 20) {
                    remove();
                    
                    players.get(i).alive = false;
                    
                    //System.out.println(players.get(i) + " Died");
                    level.add(new ParticleSpawner((int) x, (int) y, 44, 50, level));
                    remove();
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


