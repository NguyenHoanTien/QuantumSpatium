
package Entity.spawner;

import Entity.partical.Particle;
import Graphics.Sprite;
import level.Level;


public class ParticleSpawner extends Spawner{

    private int life;
    
    public ParticleSpawner(int x, int y, int life, int amount, Level level, Sprite sprite) {
        super(x, y, Type.PARTICLE, amount, level);
        this.life = life;
        for (int i = 0; i < amount; i++ ) {
                level.add(new Particle(x, y, life, sprite));
        }
    }
    
}
