
package Entity.projectile;

import Entity.Entity;
import Graphics.Sprite;
import java.util.Random;

public abstract class Projectile extends Entity{
    
    protected final int xOrigin, yOrigin;
    protected double angle;
    protected Sprite sprite;
    protected double nx, ny;
    protected double speed, range, damage;
    protected double x, y;
    protected double distance;
    
    protected final Random random = new Random(); 
    
    public Projectile (int x, int y, double dir) {
        xOrigin = x; 
        yOrigin = y;
        angle = dir;
        this.x = x;
        this.y = y;
    }
    
    public Sprite getSprite() {
        return sprite;
    }
    
    public int getSpriteSize() {
        return sprite.SIZE;
    }
    
    protected void move() {
        
    }
}
