package Entity.mob;

import Audio.Music;
import Graphics.AnimateSprite;
import Graphics.Screen;
import Graphics.Sprite;
import Graphics.SpriteSheet;
import Util.Vector2i;
import java.util.List;
import level.Node;

public class Star extends Mob {

    private AnimateSprite starAniU = new AnimateSprite(SpriteSheet.starmoveU, 32, 32, 3);
    private AnimateSprite starAniD = new AnimateSprite(SpriteSheet.starmoveD, 32, 32, 3);
    private AnimateSprite starAniL = new AnimateSprite(SpriteSheet.starmoveL, 32, 32, 3);
    private AnimateSprite starAniR = new AnimateSprite(SpriteSheet.starmoveR, 32, 32, 3);

    private AnimateSprite aniSprite;

    private int xa = 0, ya = 0;
    private List<Node> path = null;
    private int time = 0;
    private Player player;
    private double speed = 4;

    public Star(int x, int y) {
        this.x = x << 4;
        this.y = y << 4;
        sprite = Sprite.star;
        this.health = 1;
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

        int px = level.getPlayerAt(0).getX() + 15;
        int py = level.getPlayerAt(0).getY() + 15;

        Vector2i start = new Vector2i(getX() >> 4, getY() >> 4);
        Vector2i destination = new Vector2i(px >> 4, py >> 4);

        if (time % 2 == 0) {
            path = level.findpath(start, destination);
        }

        if (path != null) {
            if (path.size() > 0) {
                Vector2i vec = path.get(path.size() - 1).tile;
                if (x < (vec.getX() << 4)) {
                    aniSprite = starAniR;
                    xa += speed;
                }
                if (x > (vec.getX() << 4)) {
                    aniSprite = starAniL;
                    xa -= speed;
                }
                if (y < (vec.getY() << 4)) {
                    aniSprite = starAniD;
                    ya += speed;
                }
                if (y > (vec.getY() << 4)) {
                    aniSprite = starAniU;
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

    private int Hcount = 0;
    public void update() {
        time++;
        move();
        if (moving) {
            aniSprite.update();
        }
        Hcount++;
        if (Hcount >= 30) {
            Hcount = 0;
            Music.bip.play();
        }

    }

    public void render(Screen screen) {
        if (moving == true) {
            sprite = aniSprite.getSprite();
        } else {
            sprite = Sprite.star;
        }
        screen.renderAI((int) (x - 16), (int) (y - 16), this);
    }

}
