
package Entity.mob;

import Graphics.AnimateSprite;
import Graphics.Screen;
import Graphics.Sprite;
import Graphics.SpriteSheet;
import Util.Vector2i;
import java.util.List;
import level.Node;

public class Star extends Mob {
    private AnimateSprite starAni = new AnimateSprite(SpriteSheet.starmove, 32, 32, 3);
    private AnimateSprite aniSprite = starAni;

    private int xa = 0, ya = 0;
    private List<Node> path = null; 
    private int time = 0;
    
    public Star(int x, int y) {
        this.x = x << 4;
        this.y = y << 4;
        sprite = Sprite.star;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    private void move() {
        double speed = 4;
        xa = 0;
        ya = 0;
        
        int px = level.getPlayerAt(0).getX() + 10;
        int py = level.getPlayerAt(0).getY() + 10;
        
        Vector2i start = new Vector2i(getX()>> 4, getY() >> 4);
        Vector2i destination = new Vector2i(px >> 4, py >> 4);
        
        if (time % 2 == 0) path = level.findpath(start, destination);
        
        if(path != null ) {
            if (path.size() > 0) {
                Vector2i vec = path.get(path.size() - 1).tile;
                if (x < (vec.getX() << 4)) {
                    xa += speed;
                }
                if (x > (vec.getX() << 4)) {
                    xa -= speed;
                }
                if (y < (vec.getY() << 4)) {
                    ya += speed;
                }
                if (y > (vec.getY() << 4)) {
                    ya -= speed;
                }
            }
        }
        
        if (xa != 0 || ya != 0) {
            move(xa, ya);
            moving = true;
        } else {
            moving = false;
        }   
    }

    public void update() {
        time++;
        move();
        if (moving) {
            aniSprite.update();
        }

    }

    public void render(Screen screen) {
        if (moving == true) {
            sprite = aniSprite.getSprite();
        } else {
            sprite = Sprite.star;
        }

        screen.renderAI((int)(x - 16),(int)(y - 16), this);
    }

}
