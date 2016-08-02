package level;

import Entity.Entity;
import Entity.mob.Chaser;
import Entity.mob.Dummy;
import Entity.mob.Star;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import level.Tile.Tile;

public class SpawnLevel extends Level {

    public SpawnLevel(String path) {
        super(path);

    }

    protected void LoadLevel(String path) {
        try {
            BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
            int w = width = image.getWidth();
            int h = height = image.getHeight();
            tiles = new int[w * h];
            image.getRGB(0, 0, w, h, tiles, 0, w);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Exception! Could not load level file! ");
        }

        int a = 50;
        int b = 5;

        for (int i = 0; i < 5; i++) {           // spawn the following of AI   
            add(new Star(a, b));
            a += 10;
            b += 10; 
            //respawn();
        }

        for (int i = 0; i < 10; i++) {           // spawn the following of AI   
            add(new Chaser(20, 5));
        }

        for (int i = 0; i < 10; i++) {            // spawn the NPC of AI
            add(new Dummy(20, 5));
        }

        //respawn();
        
    }
/*
/// cai nay ko xai
    private Level level;
    private static List<Entity> deadEnemy = new ArrayList<Entity>();

    public void respawn() {
        List<Entity> entities = new ArrayList<Entity>();
        for (int i = 0; i < entities.size(); i++) {
            if (deadEnemy.contains(this)) // e dang test thoai, ke no di. ko dung toi {
            {
                level.add(new Star(50, 5));
                System.out.println("add1");
            }
            System.out.println("add");

        }
        deadEnemy.remove(this);
        System.out.println("removed");
    }
*/
    protected void generateLevel() {
    }
}
