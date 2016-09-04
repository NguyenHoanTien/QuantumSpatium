package game;

import Audio.Music;
import Entity.Entity;
import Entity.mob.Player;
import Graphics.Screen;
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
import java.io.IOException;
import java.util.logging.Logger;
import javax.swing.JFrame;
import level.Level;
import level.SpawnLevel;
import level.TileCoordinate;
import level.TutorialLevel;
import Util.Timer;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static int width = 500;
    public static int height = 268;
    public static int scale = 3;

    public static String title = "QUANTUM SPATIUM";

    //for display
    private int Playcounter = 0;

    private JFrame frame;
    private Keyboard key;
    private Entity entity;
    private Level level;
    private Player player;
    private boolean running = false;
    
    //Timer
    private Timer timeCounter = new Timer();
   
    private int duration;
    //create states for the game
    public static enum STATE {

        TUTORIAL,
        RUNTUT,
        GAME,
        MENU,
        PLAY,
        ABOUT,
        DEAD,
        OVER,
        PAUSE,
    }

    public static enum HOVER {

        TUTORIAL,
        PLAY,
        ABOUT,
        QUIT,
        BUTTON1,
        BUTTON2,
        ABOUTBACK,
        NONE
    }

    public static boolean Mcheck = false;
//    
    public static HOVER Hover;
    public static STATE State = STATE.MENU;
    private Menu menu = new Menu();
    private Level spawn;
    public static STATE prevState;
    public static int tutStep;
    public static boolean a_released = false, d_released = false;

    private Screen screen;
    private Thread thread;      /* thread is many program, it will run iot own program. 
     (like sub program with many funtion in a big program and it run separately */

    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    // conver buffered image into an array int
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

    public Game() {
        Dimension size = new Dimension(screenSize.width, screenSize.height);
        setPreferredSize(size);

        screen = new Screen(width, height);
        frame = new JFrame();
        key = new Keyboard();
        addKeyListener(key);

        Mouse mouse = new Mouse();
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }

    public static int getWindowWidth() {
        return screenSize.width;
    }

    public static int getWindowHeight() {
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
                updates++;
                delta--;
            }
            if (State == STATE.TUTORIAL) {
                if (player != null || level != null) {
                    player.remove();
                    level.remove();
                }
                spawn = new TutorialLevel("/textures/testL.png");
                if (Playcounter == 0) {
                    level = spawn;
                    tutStep = 0;
                }
                Playcounter++;
                TileCoordinate playerSpawn = new TileCoordinate(1210, 629);   // player spawn location
                player = new Player(playerSpawn.x(), playerSpawn.y(), key);
                level.add(player);

            } else if (State == STATE.PLAY) {
                if (player != null || level != null) {
                    player.remove();
                    level.remove();
                }
                spawn = new SpawnLevel("/textures/testL.png");
                if (Playcounter == 0) {
                    timeCounter.start();
                    level = spawn;
                }
                //////// Timer ////////
                timeCounter.stop();
                duration = timeCounter.getDuration();
                System.out.println(duration);
                ////////////////////////////////
                Playcounter++;
                TileCoordinate playerSpawn = new TileCoordinate(1210, 629);   // player spawn location
                player = new Player(playerSpawn.x(), playerSpawn.y(), key);
                level.add(player);
            } else if (State == STATE.OVER || State == STATE.MENU) {
                Playcounter = 0;
            }

            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                //System.out.println(updates + " ups, " + frames + " fps");
                frame.setTitle(title + "  |  " + updates + " ups, " + frames + " fps");
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }

    public void update() {
        if (State == STATE.GAME || State == STATE.RUNTUT) {
            key.update();
            level.update();
//            int a = level.getPlayerAt(0).getX();
//            int b = level.getPlayerAt(0).getY();
//            System.out.println("a : " + (a/16) + " | b: " + (b/16));
        } else if (State == STATE.DEAD) {
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

        double xScroll = 0, yScroll = 0;
        if (State != STATE.MENU && State != STATE.ABOUT) {
            xScroll = player.getX() - screen.width / 2;
            yScroll = player.getY() - screen.height / 2;
            level.render((int) xScroll, (int) yScroll, screen);
        }

        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i];
        }

        Graphics g = bs.getDrawGraphics();

        //clear screen
        g.clearRect(0, 0, getWidth(), getHeight());
        // draw hear !
        if (State == STATE.TUTORIAL) {
            if (Music.menu.isActive()) {
                Game.Mcheck = false;
                Music.menu.stop();
            }
            if (!Mcheck) {
                Mcheck = true;
                Music.tut1.play();
                Music.tut1.loop();
            }
            g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
            if (Playcounter / 100 < 2) {
                menu.tutRender(g);
            } else if (Playcounter / 100 >= 2) {
                tutStep++;
                State = STATE.RUNTUT;
                Playcounter = 0;
            }
        }
        if (State == STATE.RUNTUT) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
            menu.tutRender(g);
            if (tutStep == 5) {
                Playcounter++;
                if (Playcounter / 100 == 6) {
                    tutStep++;
                }
            }
        } else if (State == STATE.GAME || State == STATE.DEAD) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Verdana", 0, 100));
            g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
            g.drawString("" + level.count_level, 10, 90);

//            if (level.score == 0) {
//                b = 10;
//                a = 400;
//            }
//            if (level.score >= b) {
//                b = b * 10;
//                a = a + 40;
//            }
            
            System.out.println("width: " + screenSize.width + "heigth: " + screenSize.height);
            
            g.setFont(new Font("Verdana", 0, 60));
            g.drawString("Level " + level.level_state, screenSize.width / 2 - 100, 50);
            g.drawString("Score: " + level.score, screenSize.width - 500, 50);
        } else if (State == STATE.MENU) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
            menu.mainRender(g);
            a_released = false;
            d_released = false;
            tutStep = 0;

        } else if (State == STATE.PLAY) {
            if (Music.menu.isActive()) {
                Mcheck = false;
                Music.menu.stop();
            }
            if (!Mcheck) {
                Mcheck = true;
                Music.count.play();
            }

            g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
            if (duration < 3) {
                menu.playRender(g, duration );
            } else if (duration >= 3) {
                State = STATE.GAME;
                if (Mcheck) {
                    Mcheck = false;
                }
                if (!Mcheck) {
                    Mcheck = true;
                    Music.tut1.play();
                    Music.tut1.loop();
                }
            }
        } else if (State == STATE.PAUSE) {
            g.drawString("" + level.count_level, 0, 50);
            g.drawString("Score: " + level.score, screenSize.width - 400, 50);
            g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
            menu.pauseRender(g);
        } else if (State == STATE.OVER) {
            if (Music.tut1.isActive()) {
                Mcheck = false;
                Music.tut1.stop();
            }
            g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
            Font font2 = new Font("arial", Font.BOLD, 70);
            Font font3 = new Font("arial", Font.BOLD, 40);
            Font font4 = new Font("arial", Font.BOLD, 100);
            g.setColor(Color.white);
            g.setFont(font2);
            g.drawString("High Score", Game.screenSize.width - 500, Game.screenSize.height / 2 - 400);
            // score
            g.setFont(font2);
            int[] a = level.getClientPlayer().abc.getHighScore();
            int b = 300;
            for (int i = a.length - 1; i > 0; i--) {
                int actual_width = g.getFontMetrics().stringWidth(String.valueOf(a[i]));
                int x = (Game.screenSize.width - 120) - actual_width - 10;
                g.drawString("" + a[i], x, Game.screenSize.height / 2 - b);
                b -= 60;
            }
            g.setColor(Color.white);
            g.fillRect(1400, 790, 450, 5);
            g.setFont(font3);
            g.drawString("Your score: ", Game.screenSize.width - 500, Game.screenSize.height / 2 - b);
            g.setFont(font4);
            g.drawString("" + level.score, Game.screenSize.width - 500, Game.screenSize.height / 2 - b + 100);
            menu.overRender(g);
        }
        else if (State == STATE.ABOUT){
            g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
            menu.aboutRender(g);
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Verdana", 0, 50));
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
