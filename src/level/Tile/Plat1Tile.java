
package level.Tile;

import Graphics.Screen;
import Graphics.Sprite;


class Plat1Tile extends Tile {

    public Plat1Tile(Sprite sprite) {
        super(sprite);
    }
     public void render (int x, int y, Screen screen) {
        screen.renderTile(x << 4, y << 4, this);
    }
     
}
