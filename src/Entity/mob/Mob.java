package Entity.mob;

import Entity.Entity;
import Entity.projectile.MobWizardProjectile;
import Entity.projectile.Projectile;
import Entity.projectile.WizardProjectile;
import Entity.spawner.ParticleSpawner;
import Graphics.Screen;
import Graphics.Sprite;
import game.Game;


public abstract class Mob extends Entity {

    protected boolean moving = false;
         
    public void move(double xa, double ya) {

        if (xa != 0 && ya != 0) {
            move(xa, 0);
            move(0, ya);
            return;
        }
        
        while (xa != 0) {
            if (Math.abs(xa) > 1) {
                if (!collision(abs(xa), ya)) {
                    this.x += abs(xa);
                }
                xa -= abs(xa);
            } else {
                if (!collision(abs(xa), ya)) {
                    this.x += xa;
                }
                xa = 0;
            }
        }

        while (ya != 0) {
            if (Math.abs(ya) > 1) {
                if (!collision(xa, abs(ya))) {
                    this.y += abs(ya);
                }
                ya -= abs(ya);
            } else {
                if (!collision(xa, abs(ya))) {
                    this.y += ya;
                }
                ya = 0;
            }
        }
    }
    
    
    private int abs(double value) {
        if (value < 0) {
            return -1;
        }
        return 1;
    }

    public abstract void update(); 

    public abstract void render(Screen screen);

    protected void shoot(double x, double y, double dir) {
        Projectile p = new WizardProjectile(x, y, dir);
        level.add(p);
    }
    
    protected void Mobshoot(double x, double y, double dir, double butspeed, Sprite sprite) {
        Projectile p = new MobWizardProjectile(x, y, dir, butspeed, sprite);
        level.add(p);
    }

    private boolean collision(double xa, double ya) {
        boolean solid = false;
        for (int c = 0; c < 4; c++) {
            double xt = ((x + xa) - c % 2 * 42 + 13) / 16;          // player colision.
            double yt = ((y + ya) - c / 2 * 42 + 13) / 16;

            int ix = (int) Math.ceil(xt);
            int iy = (int) Math.ceil(yt);
            if (c % 2 == 0) {
                ix = (int) Math.floor(xt);
            }
            if (c / 2 == 0) {
                iy = (int) Math.floor(yt);
            }
            
            if (level.getTile(ix, iy).solid()) {
                solid = true;
            }
        }
        return solid;
    }
  
}
