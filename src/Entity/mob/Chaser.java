
package Entity.mob;

import Graphics.AnimateSprite;
import Graphics.Screen;
import Graphics.Sprite;
import Graphics.SpriteSheet;

public class Chaser extends Mob {
    
    private AnimateSprite chaserAni = new AnimateSprite(SpriteSheet.chasermove, 32, 32, 3);
    private AnimateSprite aniSprite = chaserAni;
    
    private int xa = 0, ya = 0;
     
    public Chaser (int x, int y) {
        this.x = x << 4;
        this.y = y << 4;
        sprite = Sprite.chaser;
    }
    
    private void move () {
        int speed = 3;
        xa=0;
        ya=0;
        Player player = level.getClientPlayer();
        
        if (x + 2 < player.getX() || x - 2 < player.getX()) xa+= speed;
        if (x + 2 > player.getX() || x - 2 > player.getX()) xa-= speed;
        if (y + 2 < player.getY() || y - 2 < player.getY()) ya+= speed;
        if (y + 2 > player.getY()  || y - 2 > player.getY()) ya-= speed;
        
        if (xa != 0 || ya != 0) {
            move(xa, ya);
            moving = true;
        } else {
            moving = false;
        }
    }
    
    public void update() {
        move();
        if (moving) {
            aniSprite.update();
        }

        
    }

    public void render(Screen screen) {
        if (moving == true) {
            sprite = aniSprite.getSprite();
        } else {
            sprite = Sprite.chaser;
        }
        
        screen.renderAI(x-16, y-16, this);
    }
    
}
