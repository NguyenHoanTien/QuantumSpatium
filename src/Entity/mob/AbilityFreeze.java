package Entity.mob;

import Graphics.AnimateSprite;
import Graphics.Screen;
import Graphics.Sprite;
import Graphics.SpriteSheet;
import Util.Timer;

public class AbilityFreeze extends Mob {

    private AnimateSprite Abiani = new AnimateSprite(SpriteSheet.AbilityF, 16, 16, 6, 15);
    private AnimateSprite aniSprite = Abiani;

    public boolean abilityD = false;

    private Timer timeCounter = new Timer();
    private int duration;

    public AbilityFreeze(int x, int y) {
        this.x = x << 4;
        this.y = y << 4;
        sprite = Sprite.shieldab;
        timeCounter.start();
    }

    public void update() {
        aniSprite.update();

        timeCounter.stop();
        duration = timeCounter.getDuration();

        if (duration > 4) {
            remove();
        }
    }

    public void render(Screen screen) {
        sprite = aniSprite.getSprite();
        screen.renderAP((int) (x - 5), (int) (y - 5), this);
    }

}
