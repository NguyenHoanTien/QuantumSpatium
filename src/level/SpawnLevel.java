package level;

import Entity.Entity;
import Entity.mob.Chaser;
import Entity.mob.Dummy;
import Entity.mob.Star;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
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
        for (int i = 0; i < 1; i++) {           // spawn the following of AI   
            add(new Star(50, 5));
        }
        
        for (int i = 0; i < 1; i++) {           // spawn the following of AI   
            add(new Chaser(20, 5));
        }

        for (int i = 0; i < 5; i++) {            // spawn the NPC of AI
            add(new Dummy(20, 5));
        }
        
    }   

    protected void generateLevel() {
    }
}
