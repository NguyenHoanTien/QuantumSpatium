package level;

import Entity.Entity;
import Entity.mob.Player;
import Entity.spawner.Spawner;
import Entity.partical.Particle;
import Entity.projectile.Projectile;
import Graphics.Screen;
import java.util.ArrayList;
import java.util.List;
import level.Tile.Tile;

public class Level {

    protected int width, height;
    protected int[] tilesInt;
    protected int[] tiles;
    protected int tile_size;

    private List<Entity> entities = new ArrayList<Entity>();
    public List<Projectile> projectiles = new ArrayList<Projectile>();
    public List<Particle> particles = new ArrayList<Particle>();
    private List<Player> players = new ArrayList<Player>();
    
    public static Level spawn = new SpawnLevel("/textures/level.png");

    
    
    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        tilesInt = new int[width * height];
        generateLevel();
    }

    public Level(String path) {
        LoadLevel(path);
        generateLevel();
        
    }

    protected void generateLevel() {
        for (int y = 0; y < 64; y++) {
            for (int x = 0; x < 64; x++) {
                getTile(x, y);
            }
        }
        tile_size = 16;
    }

    protected void LoadLevel(String path) {

    }

    public void update() {
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).update();
        }
        for (int i = 0; i < projectiles.size(); i++) {
            projectiles.get(i).update();
        }
        for (int i = 0; i < particles.size(); i++) {
            particles.get(i).update();
        }
        for (int i = 0; i < particles.size(); i++) {
            particles.get(i).update();
        }
        for (int i = 0; i < players.size(); i++) {
            players.get(i).update();
        }
        remove();
    }

    private void remove() {
        for (int i = 0; i < entities.size(); i++) {
            if(entities.get(i).isRemoved()) entities.remove(i);        
        }
        for (int i = 0; i < projectiles.size(); i++) {
            if(projectiles.get(i).isRemoved()) projectiles.remove(i);
        }
        for (int i = 0; i < particles.size(); i++) {
            if(particles.get(i).isRemoved()) particles.remove(i);
        }
        for (int i = 0; i < players.size(); i++) {
            if(players.get(i).isRemoved()) players.remove(i);
        }
    }
    
    public List<Projectile> getProjectiles() {
        return projectiles;
    }

    private void time() {

    }

    public boolean tileCollision(int x, int y, int size, int xOffset, int yOffset) {
        boolean solid = false;
        for (int c = 0; c < 4; c++) {
            int xt = (x - c % 2 * size + xOffset ) / 16;      // /70
            int yt = (y - c / 2 * size + yOffset) / 16;      // /10 - 10
            if (getTile(xt, yt).solid()) {
                solid = true;
            }
        }
        return solid;
    }

    public void render(int xScroll, int yScroll, Screen screen) {
        screen.setOffset(xScroll, yScroll);
        int x0 = xScroll >> 4;    // mean /16
        int x1 = (xScroll + screen.width + 16) >> 4;
        int y0 = yScroll >> 4;    // mean /16
        int y1 = (yScroll + screen.height + 16) >> 4;

        // Level render
        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                getTile(x, y).render(x, y, screen);
            }
        }

        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).render(screen);
        }
        for (int i = 0; i < projectiles.size(); i++) {
            projectiles.get(i).render(screen);
        }
        for (int i = 0; i < particles.size(); i++) {
            particles.get(i).render(screen);
        }
        for (int i = 0; i < players.size(); i++) {
            players.get(i).render(screen);
        }
    }

    public void add(Entity e) {
        e.init(this);
        if (e instanceof Particle) {
            particles.add((Particle) e);
        } else if (e instanceof Projectile) {
            projectiles.add((Projectile) e);
        } else if (e instanceof Player) {
            players.add((Player) e);
        }else {
            entities.add(e);
        }

    }
    
    public List<Player> getPlayers() {
        return players;
    }

    public Player getPlayerAt (int index) {
        return players.get(index);
    }
    
    public Player getClientPlayer() {
        return players.get(0);
    }
    
    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            return Tile.voidTile;
        }
        if (tiles[x + y * width] == Tile.col_plat) {
            return Tile.plat;
        }
        if (tiles[x + y * width] == Tile.col_plat1) {
            return Tile.plat1;
        }
        if (tiles[x + y * width] == Tile.col_plat2) {
            return Tile.plat2;
        }
        if (tiles[x + y * width] == Tile.col_wall) {
            return Tile.wall;
        }
        if (tiles[x + y * width] == Tile.col_block) {
            return Tile.block;
        }
        return Tile.voidTile;
    }
}
