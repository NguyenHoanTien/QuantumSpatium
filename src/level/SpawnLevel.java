package level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import level.respawnMob;

public class SpawnLevel extends Level {

    private Thread threadrespawn;
            
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
        
        //respawn.spawnStart();
        threadrespawn = new Thread(new respawnMob());      // This mean the game class
        threadrespawn.start();                 // Start up thread and call the run menthod
        
//        respawnMob threadrespawn = new respawnMob();
//        threadrespawn.start();
    }

 
    protected void generateLevel() {
    }
}
