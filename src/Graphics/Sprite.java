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
    
    public static Sprite dummy = new Sprite (32,0 ,0, SpriteSheet.Enemy);
    public static Sprite chaser = new Sprite (32,1 ,0, SpriteSheet.Enemy);
    
    public static Sprite wall = new Sprite (16, 2, 0, SpriteSheet.tiles);
    public static Sprite block = new Sprite (16, 1, 0, SpriteSheet.tiles);
    
    public static Sprite playerStop = new Sprite (32, 0, 7, SpriteSheet.tiles);
    
    // Dummy 
    
    
    // void sprite
    public static Sprite voidSprite = new Sprite (16,0);
    
    // bullet animation;
    public static Sprite bullet = new Sprite (16, 1, 1, SpriteSheet.tiles);
    

    // Projectile Sprite
    public static Sprite shoot = new Sprite (16, 1, 1, SpriteSheet.tiles);
    
    // particle Sprite
    public static Sprite particle_normal = new Sprite (3, 0x0094FF);
    
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
