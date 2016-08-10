package Entity.mob;

import Entity.projectile.MobWizardProjectile;
import Graphics.AnimateSprite;
import Graphics.Screen;
import Graphics.Sprite;
import Graphics.SpriteSheet;
import java.util.List;
import java.util.Random;

public class Chaser extends Mob {

    private AnimateSprite chaserAni = new AnimateSprite(SpriteSheet.chasermove, 32, 32, 3);
    private AnimateSprite aniSprite = chaserAni;
    
    private double xa = 0, ya = 0;
    private double xp = 0, yp = 0;
    private int time = 0;
    private double speed = 2.5;
    private double dir;

    public int Firerate = 0;  

    private boolean checkMove = false;
    private int countcheck = 0;

    public Chaser(int x, int y) {
        this.x = x << 4;
        this.y = y << 4;
        sprite = Sprite.chaser;
        this.health = 3;
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
        List<Player> players = level.getPlayers(this, 400); // range to follow player

        if (players.size() > 0) {
            Player player = players.get(0);

            if (x + 50 < player.getX()) {      // range for AI stop following player
                xa += speed;
            }

            if (x - 50 > player.getX()) {
                xa -= speed;
            }

            if (y + 50 < player.getY()) {
                ya += speed;
            }

            if (y - 50 > player.getY()) {
                ya -= speed;
            }
        }

        if (xa != 0 || ya != 0) {
            move(xa, ya);
            moving = true;
            checkMove = true;
        } else {
            moving = false;
            if (x > 50 || y > 50) {
                checkMove = false;
            }
        }
    }

    public void update() {
        move();
        time++;
        countcheck++;

        if (Firerate > 0) {
            Firerate--;
        }

        if (checkMove == false && countcheck > 5) {
            if (time % (random.nextInt(50) + 30) == 0) {
                xp = random.nextInt(3) - 1;
                yp = random.nextInt(3) - 1;
                if (random.nextInt(3) == 0) {
                    xp = 0;
                    yp = 0;
                }
            }

            if (xp != 0 || yp != 0) {
                move(xp, yp);
                moving = true;
            } else {
                moving = false;
            }
        } else if (checkMove == true) {
            countcheck = 0;

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

                Mobshoot(x, y, Accu, 3);
                Mobshoot(x, y, dir, 3);
                Firerate = MobWizardProjectile.FireRate;
                /////////////////////////
            }
        }
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

        screen.renderAI((int) (x - 16), (int) (y - 16), this);
    }

}
