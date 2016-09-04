package Entity.mob;

import Audio.Music;
import Entity.Entity;
import Entity.projectile.Projectile;
import Entity.projectile.FreezeWizardProjectile;
import Entity.projectile.WizardProjectile;
import Entity.spawner.ParticleSpawner;
import Graphics.AnimateSprite;
import Graphics.Screen;
import Graphics.Sprite;
import Graphics.SpriteSheet;
import Input.Keyboard;
import Input.Mouse;
import Util.Timer;
import game.Game;
import game.HighScore;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class Player extends Mob {

    private Keyboard input;
    private Sprite sprite;
    private Sprite sprite1;

    public int heal = 1;

    public static int firerate = 0;
    public static boolean check = false;

    private double speed = 3;
    private AnimateSprite playerAni = new AnimateSprite(SpriteSheet.playermove, 32, 32, 3, 7);
    private AnimateSprite playerAni1 = new AnimateSprite(SpriteSheet.playermoveback, 32, 32, 3, 7);

    public boolean alive = true;

    private double dir_player = 0;
    private double nx, ny;

    private int timer = 0;
    public HighScore abc;
    private Abilityshoot Abilityshoot;

    private Timer timeCounter = new Timer();
    public int duration = 0;
    
    public int limit = 5;  // End time of shooting ability
    public boolean checkShoot = false;
    public boolean checkFreeze = false;
    public boolean Freeze = false;
    
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
        double xa = 0;
        double ya = 0;

        
        if (alive) {
            if (firerate > 0) {
                firerate--;
            }

            if (input.up) {
                //ya -= speed;
                ya += ny;
                xa += nx;
                sprite1 = playerAni.getSprite();
            }
            if (input.down) {
                //ya += speed;
                ya -= ny / 2;
                xa -= nx / 2;
                sprite1 = playerAni1.getSprite();
            }
            if (input.left) {
                //xa -= speed;
                dir_player -= 0.1;
            }
            if (input.right) {
                //xa += speed;
                dir_player += 0.1;
            }
            if (xa != 0 || ya != 0) {
                move(xa, ya);
                moving = true;
            } else {
                moving = false;
            }

            updateRotating();
            clear();

            List<Entity> entities = level.entities;
            updateMobCollision(entities);  // mob collision, player will destroy when collision with mob
            //System.out.println ("player heal: " + heal); // check heal of player

            updateAP(entities);
            updateShooting();

        } else {
            Game.State = Game.STATE.DEAD;
            timer++;
            if (timer > 50) {
                int score = level.score;
                abc = new HighScore();
                try {
                    abc.setScore(score);
                    abc.getHighScore();
                } catch (IOException ex) {
                    Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
                int[] a = abc.getHighScore();
                Game.State = Game.STATE.OVER;
            }
        }

    }

    public void updateMobCollision(List<Entity> entities) {
        for (int i = 0; i < entities.size(); i++) { // check the list of entities.
            if (entities.get(i) != null && entities.get(i) instanceof Dummy || // these kind of mob will do the action
                    entities.get(i) instanceof Chaser
                    || entities.get(i) instanceof Star) {
                if (x < entities.get(i).getX() + 15 // range that will do the collision when hit the mob
                        && x > entities.get(i).getX() - 15
                        && y < entities.get(i).getY() + 15
                        && y > entities.get(i).getY() - 15) {

                    heal = 0;               // when player hit the mob, heal = 0, mean die directly

                    level.add(new ParticleSpawner((int) x, (int) y, 44, 25, level, Sprite.particle_gray));    // display partical effect
                    level.add(new ParticleSpawner((int) x, (int) y, 44, 25, level, Sprite.particle_blue));
                    if (heal == 0) {        // condition.
                        entities.get(i).remove();       // remove the mob that hit player
                        //System.out.println(" Died");  // checking...
                        //remove();                       // remove player.
                        alive = false;
                        Music.exp.play();
                    }
                    /* the reason why we have to remove mob and player: if we don't remove mob
                     system won't understand the syntax and display error null exception
                     => have to delete both */
                }
            }
        }
    }

    
    public void updateAP(List<Entity> entities) {
        for (int i = 0; i < entities.size(); i++) { // check the list of entities.
            if (entities.get(i) != null && entities.get(i) instanceof Abilityshoot) {
                if (x < entities.get(i).getX() + 15 // range that will do the collision when hit the mob
                        && x > entities.get(i).getX() - 15
                        && y < entities.get(i).getY() + 15
                        && y > entities.get(i).getY() - 15) {
                    // display partical effect
                    entities.get(i).remove();       // remove the mob that hit player
                    checkShoot = true;
                    timeCounter.start();
                }
            } else if (entities.get(i) != null && entities.get(i) instanceof AbilityFreeze) {
                if (x < entities.get(i).getX() + 15 // range that will do the collision when hit the mob
                        && x > entities.get(i).getX() - 15
                        && y < entities.get(i).getY() + 15
                        && y > entities.get(i).getY() - 15) {
                    // display partical effect
                    entities.get(i).remove();       // remove the mob that hit player
                    //              System.out.println("Here");
                    checkFreeze = true;
                    timeCounter.start();
                }
            }
        }
        
        if (checkFreeze) {
            freeze(x, y);
            Freeze = true;
            timeCounter.stop();
            duration = timeCounter.getDuration();
        } else if (checkShoot) {
            timeCounter.stop();
            duration = timeCounter.getDuration();
        }
        
        if (limit - duration <= 0) {
            checkShoot = false;
            checkFreeze = false;
            Freeze = false;
            duration = 0;
        }
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
            if (checkShoot) {
                shoot(x, y, (dir - 0.1));
                shoot(x, y, dir);
                shoot(x, y, (dir + 0.1));
            } else {
                shoot(x, y, dir);

            }

            Music.shoot.play();
            firerate = WizardProjectile.FireRate;
        }
    }

    private void updateRotating() {
        nx = speed * Math.cos(dir_player);
        ny = speed * Math.sin(dir_player);
        //System.out.println(dir_player);
        //System.out.println(nx + " | " + ny);
    }

    public void render(Screen screen) {

        if (moving == true) {
            sprite = sprite1;
        } else {
            sprite = Sprite.playerStop;
        }

        sprite = Sprite.rotate(sprite, dir_player);
        if (alive) {
            screen.renderMob((int) (x - 16), (int) (y - 16), sprite);
        }
    }
}
