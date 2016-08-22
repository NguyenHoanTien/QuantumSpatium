
package level;

import Entity.mob.Chaser;
import Entity.mob.Dummy;
import Entity.mob.Star;
import java.util.Random;

public class respawnMob implements Runnable{
    
    protected Level level;
    public int level_num = 30;
    public int level_present = 5;
    private boolean running = false;
    private Thread thread;
    
    public respawnMob () {
        
    }

    public int get_level_num() {
        return level_num;
    }
    public void decrement_level_num() {
        level_num--;
    }
    
    public void spawnMob() {
        Random rand = new Random();
        int x = rand.nextInt(125) + 2;
        int y = rand.nextInt(125) + 2;
        int type = rand.nextInt(99);
        if (type <= 70) {
            level.add(new Chaser(x, y));
        } else if (type > 70 && type < 95) {
            level.add(new Dummy(x, y));
        } else {
            level.add(new Star(x, y));
        }
        decrement_level_num();

    }
    
    public void spawnStart(){
        for (int i = 0; i < level_present; i++) {
            System.out.println(level_num);
            spawnMob();
            
        }
    }
    
    public void run() {
       while (running == true) {
           System.out.println("aaaaaa");
       }
    
    }
    
    public void start() {
        if (running == true) {        // avoid the condition running overlap when the game already run
            return;
        }
        running = true;
        spawnStart();
    }
    
    public void stop() {
        if (running == false) {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    
}
