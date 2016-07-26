
package level.Tile;

import Graphics.Screen;
import Graphics.Sprite;

public class Tile {
    
    public Sprite sprite;
    
    public static Tile plat = new PlatTile(Sprite.plat);
    public static Tile plat1 = new Plat1Tile(Sprite.plat1);
    public static Tile plat2 = new Plat2Tile(Sprite.plat2);
    
    public static Tile wall = new WallTile(Sprite.wall);
    public static Tile block = new BlockTile(Sprite.block);
    
    //public static Tile bullet = new Bullet(Sprite.bullet);

    public static Tile voidTile = new VoidTile(Sprite.voidSprite);
    
    public static final int col_plat = 0xffff0000;
    public static final int col_plat1 = 0xffff6a00;
    public static final int col_plat2 = 0xff00ffff;
    public static final int col_wall = 0xffffff00;
    public static final int col_block = 0xff7f7f00;
    
    public Tile (Sprite sprite) {
        this.sprite = sprite;
    }
    
    public void render (int x, int y, Screen screen) {
        
    }
    
    public boolean solid () {
        return false;
    }
}
