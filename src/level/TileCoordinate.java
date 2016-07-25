
package level;

import java.util.Random;


public class TileCoordinate {
 
    private static final Random random = new Random();
    private int x, y;
    //private final int Tile_Size = 16;
    
    public TileCoordinate (int x, int y) {
        this.x = x;
        this.y = y;
     
    }

    public int x() {
        return x;
    }
    
    public int y() {
        return y;
    }
    
    public int[] xy() {
        int[] r = new int[2];
        r[0] = x;
        r[1] = y;
        return r;
    }
    
}
