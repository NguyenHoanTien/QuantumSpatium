
package level.Tile;

import Graphics.Screen;
import Graphics.Sprite;


class WallTile extends Tile {

    public WallTile(Sprite sprite) {
         super(sprite);
    }
       
    public void render (int x, int y, Screen screen) {
        screen.renderTile(x << 4, y << 4, this);
    }
    
    public boolean solid() {
        return true;
    }
    
}
