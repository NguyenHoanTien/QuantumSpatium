package level;

import Entity.mob.Chaser;
import Entity.mob.Dummy;
import Entity.mob.Star;
import game.Game;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

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
        
         spawnStart();
    }

 
    protected void generateLevel() {
    }
}
