
package Entity.projectile;

import Entity.Entity;
import Graphics.Sprite;
import java.util.Random;

public abstract class Projectile extends Entity{
    
    protected final double xOrigin, yOrigin;
    protected double angle;
    protected Sprite sprite;
    protected double nx, ny;
    protected double range;
    protected int bulletspeed;
    protected int damage;
    protected double x, y;
    protected double distance;
    
    
    
    protected final Random random = new Random(); 
    
    public Projectile (double x, double y, double dir) {
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
