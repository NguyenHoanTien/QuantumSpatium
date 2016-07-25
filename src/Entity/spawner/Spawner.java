
package Entity.spawner;

import Entity.Entity;
import Entity.partical.Particle;
import java.util.ArrayList;
import java.util.List;
import level.Level;

public abstract class Spawner extends Entity{
    

    public enum Type {      // create a custom variable int Type and value = to the following 
        MOB, PARTICLE;
    }
    
    private Type type;
    
    public Spawner(int x , int y, Type type, int amount, Level level) {
        init(level);
        this.x = x;
        this.y = y;
        this.type = type;
        
    }
}
