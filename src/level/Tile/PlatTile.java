
package level.Tile;

import Graphics.Screen;
import Graphics.Sprite;

public class PlatTile extends Tile {

    public PlatTile(Sprite sprite) {
        super(sprite);
    }
      
    public void render (int x, int y, Screen screen) {
        screen.renderTile(x << 4, y << 4, this);
    }
     
  
}
