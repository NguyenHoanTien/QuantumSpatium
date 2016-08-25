package level;

import Entity.Entity;
import Entity.mob.Chaser;
import Entity.mob.Dummy;
import Entity.mob.Player;
import Entity.mob.Star;
import Entity.partical.Particle;
import Entity.projectile.Projectile;
import Graphics.Screen;
import Util.Vector2i;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import level.Tile.Tile;

public class Level {

    public int level_present = 5;
    public int level_num = 20;
    public int count_level = level_num;
    public int score = 0;
        
    protected int width, height;
    protected int[] tilesInt;
    protected int[] tiles;
    protected int tile_size;

    public List<Entity> entities = new ArrayList<Entity>();
    private List<Projectile> projectiles = new ArrayList<Projectile>();
    private List<Particle> particles = new ArrayList<Particle>();
    public List<Player> players = new ArrayList<Player>();

    private Comparator<Node> nodeSorter = new Comparator<Node>() {
        public int compare(Node n0, Node n1) {
            if (n1.fCost < n0.fCost) {
                return +1;
            }
            if (n1.fCost > n0.fCost) {
                return -1;
            }
            return 0;
        }
    };

//    public static Level spawn = new SpawnLevel("/textures/testL.png");
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
        for (int i = 0; i < players.size(); i++) {
            players.get(i).update();
        }
        //System.out.println("Mobs :" + entities.size());
        this.remove();
    }
    

    public int get_level_num() {
        return level_num;
   }
    public void decrement_level_num() {
        level_num--;
    }
    
    public void decrement_level_display () {
        count_level --;
    }

    public void spawnMob() {
        Random rand = new Random();
        int x = rand.nextInt(125) + 2;
        int y = rand.nextInt(125) + 2;
        int type = rand.nextInt(99);
        if (type <= 50) {
           add(new Dummy(x, y));
        } else if (type > 50 && type < 93) {
            add(new Chaser(x, y));
        } else {
            add(new Star(x, y));
        }
        decrement_level_num();

    }
    

    
    public void spawnStart(){
        for (int i = 0; i < level_present; i++) {
            spawnMob();
            //System.out.println(level_num);
        }
    }

    public void remove() {
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).isRemoved()) {
                entities.remove(i);
            }
        }
        for (int i = 0; i < projectiles.size(); i++) {
            if (projectiles.get(i).isRemoved()) {
                projectiles.remove(i);
            }
        }
        for (int i = 0; i < particles.size(); i++) {
            if (particles.get(i).isRemoved()) {
                particles.remove(i);
            }
        }
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).isRemoved()) {
                players.remove(i);
            }
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
            int xt = (x - c % 2 * size + xOffset) >> 4;      // /70
            int yt = (y - c / 2 * size + yOffset) >> 4;      // /10 - 10
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
        } else {
            entities.add(e);
        }

    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getPlayerAt(int index) {
        return players.get(index);
    }

    public Player getClientPlayer() {
        return players.get(0);
    }

    public List<Node> findpath(Vector2i start, Vector2i goal) {
        List<Node> openList = new ArrayList<Node>();
        List<Node> closeList = new ArrayList<Node>();
        Node current = new Node(start, null, 0, getDistance(start, goal));
        openList.add(current);
        while (openList.size() > 0) {
            Collections.sort(openList, nodeSorter);
            current = openList.get(0);

            if (current.tile.equals(goal)) {
                List<Node> path = new ArrayList<Node>();
                while (current.parent != null) {
                    path.add(current);
                    current = current.parent;
                }
                openList.clear();
                closeList.clear();
                return path;
            }
            openList.remove(current);
            closeList.add(current);
            for (int i = 0; i < 9; i++) {
                if (i == 4) {
                    continue;
                }
                int x = current.tile.getX();
                int y = current.tile.getY();
                int xi = (i % 3) - 1;
                int yi = (i / 3) - 1;
                Tile at = getTile(x + xi, y + yi);
                if (at == null) {
                    continue;
                }
                if (at.solid()) {
                    continue;
                }
                Vector2i a = new Vector2i(x + xi, y + yi);
                double gCost = current.gCost + (getDistance(current.tile, a) == 1 ? 1 : 0.95);
                double hCost = getDistance(a, goal);
                Node node = new Node(a, current, gCost, hCost);
                if (vecInList(closeList, a) && gCost >= node.gCost) {
                    continue;
                }
                if (!vecInList(openList, a) || gCost < node.gCost) {
                    openList.add(node);
                }
                ////////////////////
            }
        }
        closeList.clear();
        return null;
    }

    private boolean vecInList(List<Node> list, Vector2i vector) {
        for (Node n : list) {    // for every single node
            if (n.tile.equals(vector)) {
                return true;
            }
        }
        return false;
    }

    private double getDistance(Vector2i tile, Vector2i goal) {
        double dx = tile.getX() - goal.getX();
        double dy = tile.getY() - goal.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);
        return distance;
    }

    public List<Entity> getEntities(Entity e, int radius) {
        List<Entity> result = new ArrayList<Entity>();
        int ex = (int) e.getX();
        int ey = (int) e.getY();
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            int x = (int) entity.getX();
            int y = (int) entity.getY();
            int dx = Math.abs(x - ex);
            int dy = Math.abs(y - ey);
            double distance = Math.sqrt((dx * dx) + (dy * dy));
            if (distance <= radius) {
                result.add(entity);
            }
        }
        return result;
    }

    public List<Player> getPlayers(Entity e, int radius) {
        List<Player> result = new ArrayList<Player>();
        int ex = (int) e.getX();
        int ey = (int) e.getY();
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            int x = (int) player.getX();
            int y = (int) player.getY();
            int dx = Math.abs(x - ex);
            int dy = Math.abs(y - ey);
            double distance = Math.sqrt((dx * dx) + (dy * dy));
            if (distance <= radius) {
                result.add(player);
            }
        }
        return result;
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
