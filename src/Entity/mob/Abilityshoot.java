
package Entity.mob;

import Graphics.AnimateSprite;
import Graphics.Screen;
import Graphics.Sprite;
import Graphics.SpriteSheet;


public class Abilityshoot extends Mob{
    private AnimateSprite Abiani = new AnimateSprite(SpriteSheet.AbilityS, 16, 16, 3);
    private AnimateSprite aniSprite = Abiani;
    
    public boolean abilityS = false;
    private int time = 0;
    private int life = 150;
    
    public Abilityshoot (int x, int y) {
        this.x = x << 4;
        this.y = y << 4;
        sprite = Sprite.shootab;
    }
    
    public boolean abilityS() {
        abilityS = true;
        return abilityS;
    }
    
    public void update() {
        aniSprite.update();
        time++;
        if (time >= 7400 ) time = 0;
        if (time > life) {
            remove();
        }
    }

    public void render(Screen screen) {
        sprite = aniSprite.getSprite();
        
        screen.renderAP((int) (x - 5), (int) (y - 5), this);
    }
    
}
