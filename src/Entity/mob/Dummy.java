package Entity.mob;

import Graphics.AnimateSprite;
import Graphics.Screen;
import Graphics.Sprite;
import Graphics.SpriteSheet;

public class Dummy extends Mob {

    private AnimateSprite dummyAni = new AnimateSprite(SpriteSheet.dummymove, 32, 32, 3);

    private AnimateSprite aniSprite = dummyAni;
    private int xa = 0, ya = 0;

    private int time = 0;

    public Dummy(int x, int y) {
        this.x = x << 4;
        this.y = y << 4;
        sprite = Sprite.dummy;
    }

    public void update() {
        int speed = 3;
        time++;

        if (time % (random.nextInt(50) + 30) == 0) {
            xa = random.nextInt(3) - 1;
            ya = random.nextInt(3) - 1;
            if (random.nextInt(4) == 0) {
                xa = 0;
                ya = 0;
            }
        }

        if (moving) {
            aniSprite.update();
        }

        if (ya > 0) {
            aniSprite = dummyAni;
            dir = Direction.UP;
        } else if (ya > 0) {
            aniSprite = dummyAni;
            dir = Direction.DOWN;
        }

        if (xa < 0) {
            aniSprite = dummyAni;
            dir = Direction.LEFT;
        } else if (xa > 0) {
            aniSprite = dummyAni;
            dir = Direction.RIGHT;
        }

        if (xa != 0 || ya != 0) {
            move(xa, ya);
            moving = true;
        } else {
            moving = false;
        }
    }

    public void render(Screen screen) {
        if (moving == true) {
            sprite = aniSprite.getSprite();
        } else {
            sprite = Sprite.dummy;
        }

        screen.renderAI(x - 16, y - 16, this);
    }
}
