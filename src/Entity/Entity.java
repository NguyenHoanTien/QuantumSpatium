
package Entity;

import Graphics.Screen;
import Graphics.Sprite;
import java.util.Random;
import level.Level;


public class Entity {

    protected int x, y;
    private boolean removed = false;
    protected Level level;
    protected final Random random = new Random();
    protected Sprite sprite;

    public Entity () {
        
    }
    
    public Entity(int x, int y, Sprite sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }
    
    public void update(){
        
    }
    
    public void render(Screen screen) {
       if (sprite != null) screen.renderSprite (x,y,sprite,true);
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public void remove () { // remove from level
        removed = true;
    }
    
    public Sprite getSprite () {
        return sprite;
    }
    
    public boolean isRemoved () {
        return removed;
    }
    
    public void init (Level level) {
        this.level = level;
        
    }
}
