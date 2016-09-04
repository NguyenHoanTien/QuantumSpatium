package Entity.projectile;

import Audio.Music;
import Entity.Entity;
import Entity.mob.Chaser;
import Entity.mob.Dummy;
import Entity.mob.Player;
import Entity.mob.Star;
import Graphics.AnimateSprite;
import Graphics.Screen;
import Graphics.Sprite;
import Graphics.SpriteSheet;
import java.util.List;

public class FreezeWizardProjectile extends Projectile {

    private Player player;

    public FreezeWizardProjectile(double x, double y) {
        super(x, y);
        damage = 5;
        sprite = Sprite.freeze;
        
    }

    public void update() {
        List<Entity> entities = level.entities;
        updateMobCollision(entities);
        List<Projectile> projectiles = level.projectiles;
        updateprojectCollision(projectiles);
        move();
    }

    public void updateMobCollision(List<Entity> entities) {
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i) instanceof Dummy
                    || entities.get(i) instanceof Chaser
                    || entities.get(i) instanceof Star) {
                if (x < entities.get(i).getX() + 35
                        && x > entities.get(i).getX() - 35
                        && y < entities.get(i).getY() + 35
                        && y > entities.get(i).getY() - 35) {
                    //System.out.println("HERE");
                    entities.get(i).damageHealth(damage);
                    remove();
                }
            }
        }
    }

    public void updateprojectCollision(List<Projectile> projectile) {
        for (int i = 0; i < projectile.size(); i++) {
            if (projectile.get(i) instanceof MobWizardProjectile) {
                projectile.get(i).remove();
                remove();
            }
        }
    }

    protected void move() {
        nx = level.getClientPlayer().getX();
        ny = level.getClientPlayer().getY();
        x += nx;
        y += ny;
    }

    public void render(Screen screen) {
        screen.renderProjectile((int) x - 16, (int) y - 17, this);   // bullet shooting potition
    }
}
