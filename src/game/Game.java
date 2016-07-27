package game;

import Entity.mob.Player;
import Graphics.Screen;
import Graphics.Sprite;
import Graphics.SpriteSheet;
import Input.Keyboard;
import Input.Mouse;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;
import javax.swing.JFrame;
import level.Level;
import level.SpawnLevel;
import level.TileCoordinate;

public class Game extends Canvas implements Runnable{
    private static final long serialVersionUID = 1L;
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static int width = 500;
    public static int height = 268;
    public static int scale = 3;          
    public static String title = "Infinity War";

    private JFrame frame;
    private Keyboard key;
    private Level level;
    private Player player;
    private boolean running = false;
    
    //create states for the game
    public static enum STATE {

        GAME,
        MENU,
        PLAY
    }

    public static STATE State = STATE.MENU;
    private Menu menu = new Menu();
    private Screen screen;
    private Thread thread;      /* thread is many program, it will run it own program. 
     (like sub program with many funtion in a big program and it run separately */
    
    private BufferedImage image = new BufferedImage (width, height, BufferedImage.TYPE_INT_RGB);
    // conver buffered image into an array int
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

    
    public Game() {
        Dimension size = new Dimension (screenSize.width, screenSize.height);
        setPreferredSize(size);
        
        screen = new Screen(width, height);
        frame = new JFrame();
        key = new Keyboard();
        level = Level.spawn;
        TileCoordinate playerSpawn = new TileCoordinate (100,100);   // player spawn location
        player = new Player(playerSpawn.x(), playerSpawn.y(), key);
        level.add(player);

        addKeyListener(key);
        
        Mouse mouse = new Mouse();
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }
    
    public static int getWindowWidth (){
        return screenSize.width;
    }
    
      public static int getWindowHeight (){
        return screenSize.height;
    }
    
    
    public synchronized void start() {  // Start up a thread
        // Synchronized keep everything not mess up in the progess
        if (running == true) {        // avoid the condition running overlap when the game already run
            return;
        }
        running = true;
        thread = new Thread(this);      // This mean the game class
        thread.start();                 // Start up thread and call the run menthod
    }

    public synchronized void stop() {   // Stopper a thread        
        if (running == false) {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public void run() {         // the run method will run when the start method run
        
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60.0;
        double delta = 0;
        int frames = 0;
        int updates = 0;
        requestFocus();
         
        
        while (running == true) {   // when the running keep true, the variable will loop over and over
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            
            while (delta >= 1) {
                update();
                updates ++;
                delta--;
            }
            render();
            frames++;
            
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println ( updates + " ups, " + frames + " fps");
                frame.setTitle(title + "  |  " + updates + " ups, " + frames + " fps");
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }
    
    public void update() {
        if (State == STATE.GAME) {
            key.update();
            level.update();
        }
    }
    
    public void render() {
        BufferStrategy bs = getBufferStrategy();
        
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        
        screen.clear();
        
        double xScroll = player.getX() - screen.width / 2;
        double yScroll = player.getY() - screen.height / 2;
        if (State ==STATE.MENU)
            level.render(screenSize.width/2,screenSize.height/2 - 45,screen);
        else
            level.render((int) xScroll, (int)yScroll, screen);
           
        for (int i = 0; i < pixels.length; i++) {
            pixels [i] = screen.pixels[i];
        }
        
        Graphics g = bs.getDrawGraphics();
        
        //clear screen
        g.clearRect(0, 0,getWidth(), getHeight());
        // draw hear !

        if (State == STATE.GAME) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        } else {
            if (State == STATE.MENU) {
                g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
                menu.mainRender(g);
            } else if (State == State.PLAY) {
                g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
                menu.playRender(g);
            }
        }
        
        g.setColor (Color.WHITE);
        g.setFont(new Font("Verdana", 0, 50 ));
        //g.fillRect (Mouse.getX() - 32,Mouse.getY() - 32, 64,64);
        
        // end draw !
        g.dispose();
        bs.show();
    }
    
    public static void main(String[] args) {
        Game game = new Game();
        game.frame.setResizable(false);
        game.frame.setTitle(Game.title);
        game.frame.add(game);
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        game.frame.setLocationRelativeTo(null);  // the window will pop up at the center of the screen
        game.frame.setVisible(true);     // set JFrame visible (default is not visible so need to set up)
        
        game.start();
    }
}
