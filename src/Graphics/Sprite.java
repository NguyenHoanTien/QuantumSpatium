package Graphics;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


public class Sprite {
    
    public final int SIZE; 
    private int x, y;
    public int[] pixels;
    protected SpriteSheet sheet;
    private int width, height;
    
    // Spawn level Sprite here     
    public static Sprite plat = new Sprite (16, 0, 0, SpriteSheet.tiles);
    public static Sprite plat1 = new Sprite (16, 0, 1, SpriteSheet.tiles);
    public static Sprite plat2 = new Sprite (16, 0, 2, SpriteSheet.tiles);
    
    public static Sprite dummy = new Sprite (32,0 ,0, SpriteSheet.EnemyT);
    public static Sprite chaser = new Sprite (32,1,0, SpriteSheet.EnemyT);
    
    public static Sprite star = new Sprite (32,2 ,0, SpriteSheet.Enemy);
    
    public static Sprite wall = new Sprite (16, 2, 0, SpriteSheet.tiles);
    public static Sprite block = new Sprite (16, 1, 0, SpriteSheet.tiles);
    
    public static Sprite playerStop = new Sprite (32, 0, 7, SpriteSheet.tiles);
    
    public static Sprite shootab = new Sprite (16, 0, 0, SpriteSheet.Ability);
    public static Sprite shieldab = new Sprite (16, 1, 0, SpriteSheet.Ability);
    
    // void sprite
    public static Sprite voidSprite = new Sprite (16,0);
    
    // bullet animation;
    public static Sprite bullet = new Sprite (16, 1, 1, SpriteSheet.tiles);
    public static Sprite bullet1 = new Sprite (16, 1, 2, SpriteSheet.tiles);

    // Projectile Sprite
    public static Sprite shoot = new Sprite (16, 2, 1, SpriteSheet.tiles);
    public static Sprite freeze = new Sprite (32, 2, 0, SpriteSheet.playerIcon);
    // particle Sprite
    public static Sprite particle_blue = new Sprite (3, 0x91D1FF);
    public static Sprite particle_gray = new Sprite (3, 0xEEEEEE);
    public static Sprite particle_green = new Sprite (3, 0xB7E7B2);
    public static Sprite particle_purple = new Sprite (3, 0x9FA7FF);
    public static Sprite particle_yellow = new Sprite (3, 0xFFF0A8);
    public static Sprite particle_red = new Sprite (3, 0xFFAAAA);
    
    
    protected Sprite (SpriteSheet sheet, int width, int height) {
        SIZE = (width == height) ? width : -1;
        this.width = width;
        this.height = height;
        this.sheet = sheet;
        
    }
    
    public Sprite (int size, int x, int y, SpriteSheet sheet) {
        SIZE = size;
        this.width = size;
        this.height = size;
        pixels = new int [SIZE * SIZE];
        this.x = x * size;
        this.y = y * size;
        this.sheet = sheet;
        load();
    }
    
    public Sprite (int width, int height, int colour) {
        SIZE = -1;
        this.width = width;
        this.height = height;
        pixels = new int[width*height]; 
        setColour(colour);
        
    }
    
    public Sprite(int size,int colour) {
        SIZE = size;
        this.width = size;
        this.height = size;
        pixels = new int [SIZE * SIZE];
        setColour(colour);
    }
    
    public Sprite (int[] pixels, int width, int height) {
        SIZE = (width == height) ? width : -1;
        this.width = width;
        this.height = height;
        this.pixels = pixels;
    }
    
    public static Sprite rotate(Sprite sprite, double angle ) {
        return new Sprite (rotate(sprite.pixels, sprite.width, sprite.height, angle), sprite.width, sprite.height);
    }
    
    private static int[] rotate(int[] pixels, int width, int height, double angle) {
        int result[] = new int[width * height];

        double nx_x = rot_x(-angle, 1.0, 0.0);
        double nx_y = rot_y(-angle, 1.0, 0.0);
        double ny_x = rot_x(-angle, 0.0, 1.0);
        double ny_y = rot_y(-angle, 0.0, 1.0);

        double x0 = rot_x(-angle, -width / 2.0, -height / 2.0) + width / 2.0;
        double y0 = rot_y(-angle, -width / 2.0, -height / 2.0) + height / 2.0;

        for (int y = 0; y < height; y++) {
            double x1 = x0;
            double y1 = y0;
            for (int x = 0; x < width; x++) {
                int xx = (int) x1;
                int yy = (int) y1;
                int col = 0;
                if (xx < 0 || xx >= width || yy < 0 || yy >= height) {
                    col = 0xffffffff;
                } else {
                    col = pixels[xx + yy * width];
                }
                result[x + y * width] = col;
                x1 += nx_x;
                y1 += nx_y;
            }
            x0 += ny_x;
            y0 += ny_y;
        }

        return result;
    }

    private static double rot_x(double angle, double x, double y) {
        double cos = Math.cos(angle - Math.PI / 2);
        double sin = Math.sin(angle - Math.PI / 2);
        return x * cos + y * -sin;
    }

    private static double rot_y(double angle, double x, double y) {
        double cos = Math.cos(angle - Math.PI / 2);
        double sin = Math.sin(angle - Math.PI / 2);
        return x * sin + y * cos;
    }

    private void setColour(int colour) {
       for (int i = 0; i < width * height; i++) {
           pixels[i] = colour;
       }
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    private void load () {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixels[x+y*width] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.WIDTH];
            }
            
        }
    }


    
}
