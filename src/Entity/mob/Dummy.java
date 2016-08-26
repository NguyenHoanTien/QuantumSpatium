package Entity.mob;

import Audio.Music;
import Entity.projectile.MobWizardProjectile;
import Graphics.AnimateSprite;
import Graphics.Screen;
import Graphics.Sprite;
import Graphics.SpriteSheet;
import Util.Vector2i;
import java.util.List;
import java.util.Random;
import level.Node;

public class Dummy extends Mob {

    private AnimateSprite dummyAni = new AnimateSprite(SpriteSheet.dummymove, 32, 32, 3);

    private AnimateSprite aniSprite = dummyAni;

    private int xa = 0, ya = 0;
    private List<Node> path = null;
    private int time = 0;
    private Player player;
    private double speed = 1;

    private double dir;
    public int Firerate = 0;

    public Dummy(int x, int y) {
        this.x = x << 4;
        this.y = y << 4;
        sprite = Sprite.dummy;
        this.health = 2;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private void move() {

        xa = 0;
        ya = 0;

        int px = level.getPlayerAt(0).getX() + 10;
        int py = level.getPlayerAt(0).getY() + 10;
        
        Vector2i start = new Vector2i(getX() >> 4, getY() >> 4);
        Vector2i destination = new Vector2i(px >> 4, py >> 4);
        
        if (time % 2 == 0) {
            path = level.findpath(start, destination);
        }

        if (path != null) {
            if (path.size() > 5) {
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

        if (Firerate > 0) {
            Firerate--;
        }

        /////// shooting ////////
        Player p = level.getClientPlayer();
        if (Firerate <= 0) {
            double dx = p.getX() - x;
            double dy = p.getY() - y;
            dir = Math.atan2(dy, dx);
            ///////////////Shooting Accuray/////////////////
            Random random = new Random();

            double max = 0.4;
            double min = -0.4;
            double range = max - min;

            double dir1 = random.nextDouble() * range;
            double shifted = dir1 + min;

            if (dir < 0) {
                shifted = -shifted;
            }
            double Accu = shifted + dir;
            ///////////////////////////

            Mobshoot(x, y, Accu, 2, Sprite.bullet);
            //Music.shoot1.play();
            Firerate = MobWizardProjectile.FireRateD;
            /////////////////////////
        }
    }

    public void render(Screen screen) {
        if (moving == true) {
            sprite = aniSprite.getSprite();
        } else {
            sprite = Sprite.dummy;
        }

        screen.renderAI((int) (x - 16), (int) (y - 16), this);
    }
}
