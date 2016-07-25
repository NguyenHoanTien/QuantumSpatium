
package level.Tile;

import Graphics.Screen;
import Graphics.Sprite;

class Plat2Tile extends Tile {

    public Plat2Tile(Sprite sprite) {
      super(sprite);
    }
      
    public void render (int x, int y, Screen screen) {
        screen.renderTile(x << 4, y << 4, this);
    }
     
}
