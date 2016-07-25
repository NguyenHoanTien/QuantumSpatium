package Entity.mob;

import Entity.Entity;
import Entity.partical.Particle;
import Entity.projectile.Projectile;
import Entity.projectile.WizardProjectile;
import Graphics.Screen;
import Graphics.Sprite;
import java.util.ArrayList;
import java.util.List;

public abstract class Mob extends Entity {

    protected boolean moving = false;

    protected enum Direction {
        UP, DOWN, LEFT, RIGHT 
    }
    
    protected Direction dir;
    
    public void move(int xa, int ya) {
        if (xa != 0 && ya != 0) {
            move(xa, 0);
            move(0, ya);
            return;
        }

        if (!collision(xa, ya)) {       // player collision
            x += xa;
            y += ya;
        }
    }
    
    public abstract void update();
    
    public abstract void render(Screen screen);
    
    protected void shoot(int x, int y, double dir) {
        //dir *= 180 / Math.PI;
        Projectile p = new WizardProjectile (x, y ,dir);
        level.add(p);
    }
    
    private boolean collision(int xa, int ya) {            // player colision.
        boolean solid = false;
        for (int c = 0; c < 4; c++) {
            int xt = ((x + xa) + c % 2 * 22 - 12 ) / 16;
            int yt = ((y + ya) + c / 2 * 24 - 14) / 16;
            if (level.getTile(xt, yt).solid()) solid = true;
        }
        return solid;
    }

    
    
}
