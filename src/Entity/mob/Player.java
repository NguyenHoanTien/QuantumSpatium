package Entity.mob;

import Entity.projectile.Projectile;
import Entity.projectile.WizardProjectile;
import Graphics.AnimateSprite;
import Graphics.Screen;
import Graphics.Sprite;
import Graphics.SpriteSheet;
import Input.Keyboard;
import Input.Mouse;
import game.Game;
import java.util.Random;

public class Player extends Mob {

    private Keyboard input;
    private Sprite sprite;


    private int firerate = 0;
    private int speed = 4;
    private AnimateSprite playerAni = new AnimateSprite(SpriteSheet.playermove, 32, 32, 3);

    public Player(Keyboard input) {
        this.input = input;
    }

    public Player(int x, int y, Keyboard input) {
        this.x = x;
        this.y = y;
        this.input = input;
        firerate = WizardProjectile.FireRate;

    }

    public void update() {
        playerAni.update();
        int xa = 0;
        int ya = 0;
        if (firerate > 0) {
            firerate--;
        }

        if (input.up) {
            ya -= speed;
        }
        if (input.down) {
            ya += speed;
        }
        if (input.left) {
            xa -= speed;
        }
        if (input.right) {
            xa += speed;
        }

        if (xa != 0 || ya != 0) {
            move(xa, ya);
            moving = true;
        } else {
            moving = false;
        }

        clear();
        updateShooting();

    }

    private void clear() {
        for (int i = 0; i < level.getProjectiles().size(); i++) {
            Projectile p = level.getProjectiles().get(i);
            if (p.isRemoved()) {
                level.getProjectiles().remove(i);
            }
        }
    }

    private void updateShooting() {
        if (Mouse.getButton() == 1 && firerate <= 0) {
            double dx = Mouse.getX() - Game.getWindowWidth() / 2;
            double dy = Mouse.getY() - Game.getWindowHeight() / 2;
            double dir = Math.atan2(dy, dx);

            ///////////////Shooting Accuray/////////////////
            Random random = new Random();

            double max = 0.2;
            double min = 0;
            double range = max - min;

            double dir1 = random.nextDouble() * range;
            double shifted = dir1 + min;

            if (dir < 0) {
                shifted = -shifted;
            }
            double Accu = shifted + dir;
            ///////////////////////////

            shoot(x, y, Accu);
            firerate = WizardProjectile.FireRate;
        }
    }

    public void render(Screen screen) {

        if (moving == true) {
            sprite = playerAni.getSprite();
        } else {
            sprite = Sprite.playerStop;
        }

        screen.renderMob(x - 16, y - 16, sprite);
    }
}
