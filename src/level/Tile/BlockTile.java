
package level.Tile;

import Graphics.Screen;
import Graphics.Sprite;


class BlockTile extends Tile {

    public BlockTile(Sprite sprite) {
         super(sprite);
    }
    
    public void render (int x, int y, Screen screen) {
        screen.renderTile(x << 4, y << 4, this);
    }
    
    public boolean solid() {
        return true;
    }
    
    public boolean breakable() {
        return true;
    } 
    
}
