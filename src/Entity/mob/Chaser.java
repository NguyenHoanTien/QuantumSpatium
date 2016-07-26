package Entity.mob;

import Graphics.AnimateSprite;
import Graphics.Screen;
import Graphics.Sprite;
import Graphics.SpriteSheet;
import java.util.List;

public class Chaser extends Mob {

    private AnimateSprite chaserAni = new AnimateSprite(SpriteSheet.chasermove, 32, 32, 3);
    private AnimateSprite aniSprite = chaserAni;

    private double xa = 0, ya = 0;

    public Chaser(int x, int y) {
        this.x = x << 4;
        this.y = y << 4;
        sprite = Sprite.chaser;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    private void move() {
        double speed = 3;
        xa = 0;
        ya = 0;
        List<Player> players = level.getPlayers(this, 250); // range to follow player

        if (players.size() > 0) {
            Player player = players.get(0);

            if (x + 50 < player.getX()) {      // range for AI stop following player
                xa+= speed;
            }

            if (x - 50 >  player.getX()) {
                xa-= speed;
            }
            
            if (y + 50 < player.getY()) {
                ya+= speed;
            }
            
            if (y - 50 > player.getY()) {
                ya-= speed;
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

        screen.renderAI((int)(x - 16),(int)(y - 16), this);
    }

}
