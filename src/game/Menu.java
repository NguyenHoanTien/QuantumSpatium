package game;

import Audio.Music;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.net.URL;
import Entity.mob.Player;
import level.Level;

public class Menu extends Applet {

    private Image space = null;
    private int button_extraX1 = 60, button_extraX2 = 85;
    private int button_extraY = 65;
    public static int width = (Game.screenSize.width / 2) - 150;
    public static int height = (Game.screenSize.height / 2);

    //Main menu buttons
    public static Rectangle Button1 = new Rectangle(Game.screenSize.width - 640, Game.screenSize.height / 2 - 90, 600, 75);
    public static Rectangle Button2 = new Rectangle(Game.screenSize.width - 640, Game.screenSize.height / 2 + 30, 600, 75);
    public static Rectangle Button3 = new Rectangle(Game.screenSize.width - 640, Game.screenSize.height / 2 + 150, 600, 75);
    public static Rectangle Button4 = new Rectangle(Game.screenSize.width - 640, Game.screenSize.height / 2 + 270, 600, 75);
    //play menu
    public Rectangle MenuBox = new Rectangle(width - 65, 50, 400, 500);
    //PLAY MENU Back Button input
    public static int BackX = width - 25;
    public static int BackY = 450;
    public static int BackW = 125;
    public static int BackH = 75;
    private Rectangle Back = new Rectangle(BackX, BackY, BackW, BackH);

    //PLAY MENU Start Button input
    public static int StartX = BackX + 200;
    public static int StartY = BackY;
    public static int StartW = 125;
    public static int StartH = BackH;
    private Rectangle Start = new Rectangle(StartX, StartY, StartW, StartH);

    //Game over menu
    public Rectangle Box = new Rectangle(width - 65, height - 125, 400, 250);
    public static int OverY = height + 25;
    private Rectangle OverBack = new Rectangle(BackX, OverY, BackW, BackH);
    private Rectangle Restart = new Rectangle(StartX, OverY, StartW, StartH);

    //About Back Button
    public static Rectangle AboutBack = new Rectangle(Game.screenSize.width - 200, Game.screenSize.height - 100, 175, 50);
    private boolean Hcheck = false;
    private int Hc = 0;
    private int Hcount = 0;

    private Level level;

    private Image getImage(String path) {
        Image tempImage = null;
        try {
            URL imageURL = Menu.class.getResource(path);
            tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
        } catch (Exception e) {
        }
        return tempImage;
    }

    //main menu Reder method
    public void mainRender(Graphics g) {
        if (Music.tut1.isActive()) {
            Game.Mcheck = false;
            Music.tut1.stop();
        }
        if (!Game.Mcheck) {
            Game.Mcheck = true;
            Music.menu.play();
            Music.menu.loop();
        }

        Graphics2D g2d = (Graphics2D) g;
        if (space == null) {
            space = getImage("/textures/space1.jpg");
        }
        g2d.drawImage(space, 0, 0, Game.screenSize.width, Game.screenSize.height, this);
        //basic setting for font
        Font font1;
        g.setColor(Color.white);

        //draw the title
        if (Game.screenSize.width <= 1366 && Game.screenSize.height <= 768) {
            font1 = new Font("Arial", Font.BOLD, 90);
            g.setFont(font1);
            g.drawString(Game.title, Game.screenSize.width - 950, Game.screenSize.height / 2 - 200);
        
        } else {
            font1 = new Font("Arial", Font.BOLD, 100);
            g.setFont(font1);
            g.drawString(Game.title, Game.screenSize.width - 1000, Game.screenSize.height / 2 - 200);
        }
        //draw buttons
        Font font2 = new Font("Arial", Font.BOLD, 50);
        g.setFont(font2);
        g.setColor(Color.white);
        Hcount++;
        if (Hcount >= 5) {
            Hc = 0;
        }
        if (Game.Hover == Game.HOVER.TUTORIAL) {
            Hcount = 0;
            if (Hc == 1) {
                Hcheck = true;
            } else {
                Hcheck = false;
            }
            Hc = 1;
            if (!Hcheck && Hc == 1) {
                Hcheck = true;
                Music.hover.play();
            }
            g2d.draw(Button1);
            g.drawString("TUTORIAL", Button1.x + 310, Game.screenSize.height / 2 - 35);
            g.setColor(Color.yellow);
            g.drawString("PLAY", Button2.x + 435, Game.screenSize.height / 2 + 85);
            g.drawString("ABOUT", Button2.x + 390, Game.screenSize.height / 2 + 205);
            g.drawString("QUIT", Button4.x + 447, Game.screenSize.height / 2 + 325);

        } else if (Game.Hover == Game.HOVER.PLAY) {
            Hcount = 0;
            if (Hc == 2) {
                Hcheck = true;
            } else {
                Hcheck = false;
            }
            Hc = 2;
            if (!Hcheck && Hc == 2) {
                Hcheck = true;
                Music.hover.play();
            }
            g2d.draw(Button2);
            g.drawString("PLAY", Button2.x + 430, Game.screenSize.height / 2 + 85);
            g.setColor(Color.yellow);
            g.drawString("TUTORIAL", Button1.x + 315, Game.screenSize.height / 2 - 35);
            g.drawString("ABOUT", Button2.x + 390, Game.screenSize.height / 2 + 205);
            g.drawString("QUIT", Button4.x + 447, Game.screenSize.height / 2 + 325);
        } else if (Game.Hover == Game.HOVER.ABOUT) {
            Hcount = 0;
            if (Hc == 3) {
                Hcheck = true;
            } else {
                Hcheck = false;
            }
            Hc = 3;
            if (!Hcheck && Hc == 3) {
                Hcheck = true;
                Music.hover.play();
            }
            g2d.draw(Button3);
            g.drawString("ABOUT", Button2.x + 385, Game.screenSize.height / 2 + 205);
            g.setColor(Color.yellow);
            g.drawString("TUTORIAL", Button1.x + 315, Game.screenSize.height / 2 - 35);
            g.drawString("PLAY", Button2.x + 435, Game.screenSize.height / 2 + 85);
            g.drawString("QUIT", Button4.x + 447, Game.screenSize.height / 2 + 325);
        } else if (Game.Hover == Game.HOVER.QUIT) {
            Hcount = 0;
            if (Hc == 4) {
                Hcheck = true;
            } else {
                Hcheck = false;
            }
            Hc = 4;
            if (!Hcheck && Hc == 4) {
                Hcheck = true;
                Music.hover.play();
            }
            g2d.draw(Button4);
            g.drawString("QUIT", Button4.x + 442, Game.screenSize.height / 2 + 325);
            g.setColor(Color.yellow);
            g.drawString("TUTORIAL", Button1.x + 315, Game.screenSize.height / 2 - 35);
            g.drawString("PLAY", Button2.x + 435, Game.screenSize.height / 2 + 85);
            g.drawString("ABOUT", Button2.x + 390, Game.screenSize.height / 2 + 205);
        } else {
            g.setColor(Color.yellow);
            g.drawString("TUTORIAL", Button1.x + 315, Game.screenSize.height / 2 - 35);
            g.drawString("PLAY", Button2.x + 435, Game.screenSize.height / 2 + 85);
            g.drawString("ABOUT", Button2.x + 390, Game.screenSize.height / 2 + 205);
            g.drawString("QUIT", Button4.x + 447, Game.screenSize.height / 2 + 325);
        }

    }

    //render difficulty menu after click PLAY
    public void playRender(Graphics g, int counter) {
        Graphics2D g2d = (Graphics2D) g;
        int counterInt = (int) counter;
        counterInt = 3 - counterInt;
        String counterStr = Integer.toString(counterInt);
        //basic setting for font
        Font font1 = new Font("calibri", Font.BOLD, 300);
        g.setFont(font1);
        g.setColor(Color.white);
        g.drawString(counterStr, Game.screenSize.width / 2 - 75, Game.screenSize.height / 2 + 85);

    }

    public void pauseRender(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.blue);
        g2d.draw(Box);
        g.setColor(Color.lightGray);
        g.fillRect(width - 65, height - 125, 400, 250);
        Font font1 = new Font("arial", Font.BOLD, 50);
        Font font2 = new Font("calibri", Font.BOLD, 25);
        g.setFont(font1);
        g.setColor(Color.red);
        g.drawString("PAUSED", width + 35, height - 35);
        g.setFont(font2);

        ////////
        g.setColor(Color.darkGray);
        g.fillRect(BackX, OverY, BackW, BackH);
        g.fillRect(StartX, OverY, StartW, StartH);
        g2d.draw(OverBack);
        g2d.draw(Restart);
        Hcount++;
        if (Hcount >= 5) {
            Hc = 0;
        }

        if (Game.Hover == Game.HOVER.BUTTON1) {
            Hcount = 0;
            if (Hc == 1) {
                Hcheck = true;
            } else {
                Hcheck = false;
            }
            Hc = 1;
            if (!Hcheck && Hc == 1) {
                Hcheck = true;
                Music.hover1.play();
            }

            g.setColor(Color.black);
            g.fillRect(BackX, OverY, BackW, BackH);
            g.setColor(Color.red);
            g.drawString("QUIT", BackX + 35, OverY + 45);
            g.setColor(Color.white);
            g.drawString("RESUME", StartX + 20, OverY + 45);

        } else if (Game.Hover == Game.HOVER.BUTTON2) {
            Hcount = 0;
            if (Hc == 2) {
                Hcheck = true;
            } else {
                Hcheck = false;
            }
            Hc = 2;
            if (!Hcheck && Hc == 2) {
                Hcheck = true;
                Music.hover1.play();
            }

            g.setColor(Color.black);
            g.fillRect(StartX, OverY, StartW, StartH);
            g.setColor(Color.white);
            g.drawString("QUIT", BackX + 35, OverY + 45);
            g.setColor(Color.red);
            g.drawString("RESUME", StartX + 20, OverY + 45);
        } else {
            g2d.draw(Restart);
            g2d.draw(OverBack);
            g.setColor(Color.white);
            g.drawString("QUIT", BackX + 35, OverY + 45);
            g.drawString("RESUME", StartX + 20, OverY + 45);
        }

    }

    public void overRender(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Font font1 = new Font("arial", Font.BOLD, 50);
        Font font2 = new Font("calibri", Font.BOLD, 25);
        g.setColor(Color.blue);
        g2d.draw(Box);
        g.setColor(Color.white);
        g.fillRect(width - 65, height - 125, 400, 250);
        g.setFont(font1);
        g.setColor(Color.red);
        g.drawString("GAME OVER", width - 20, height - 35);
        g.setFont(font2);
        g.setColor(Color.lightGray);
        g.fillRect(BackX, OverY, BackW, BackH);
        g.fillRect(StartX, OverY, StartW, StartH);
        g2d.draw(OverBack);
        g2d.draw(Restart);
        Hcount++;
        if (Hcount >= 5) {
            Hc = 0;
        }
        if (Game.Hover == Game.HOVER.BUTTON1) {
            Hcount = 0;
            if (Hc == 1) {
                Hcheck = true;
            } else {
                Hcheck = false;
            }
            Hc = 1;
            if (!Hcheck && Hc == 1) {
                Hcheck = true;
                Music.hover1.play();
            }
            g.setColor(Color.darkGray);
            g.fillRect(BackX, OverY, BackW, BackH);
            g.setColor(Color.red);
            g.drawString("QUIT", BackX + 35, OverY + 45);
            g.setColor(Color.white);
            g.drawString("RESTART", StartX + 18, OverY + 45);

        } else if (Game.Hover == Game.HOVER.BUTTON2) {
            Hcount = 0;
            if (Hc == 2) {
                Hcheck = true;
            } else {
                Hcheck = false;
            }
            Hc = 2;
            if (!Hcheck && Hc == 2) {
                Hcheck = true;
                Music.hover1.play();
            }

            g.setColor(Color.darkGray);
            g.fillRect(StartX, OverY, StartW, StartH);
            g.setColor(Color.white);
            g.drawString("QUIT", BackX + 35, OverY + 45);
            g.setColor(Color.red);
            g.drawString("RESTART", StartX + 18, OverY + 45);
        } else {
            g2d.draw(Restart);
            g2d.draw(OverBack);
            g.setColor(Color.white);
            g.drawString("QUIT", BackX + 35, OverY + 45);
            g.drawString("RESTART", StartX + 18, OverY + 45);
        }
    }

    public void tutRender(Graphics g) {
        Font font = new Font("arial", Font.BOLD, 50);
        g.setFont(font);
        if (Game.tutStep == 0) {
            g.setColor(Color.yellow);
            g.drawString("WELCOME SOLDIER!", Game.screenSize.width / 2 - 200, 100);
            g.drawString("THIS IS A BATTLESHIP TRAINING SECTION", Game.screenSize.width / 2 - 500, 200);
        } else if (Game.tutStep == 1) {
            g.setColor(Color.white);
            g.drawString("First step:", Game.screenSize.width / 2 - 100, 100);
            g.drawString("Use 'A' or 'D' key to change direction. ", Game.screenSize.width / 2 - 450, 200);
        } else if (Game.tutStep == 2) {
            g.setColor(Color.white);
            g.drawString("Good work! Next step:", Game.screenSize.width / 2 - 250, 100);
            g.drawString("Press 'W' key to move forward ", Game.screenSize.width / 2 - 350, 200);
        } else if (Game.tutStep == 3) {
            g.setColor(Color.white);
            g.drawString("Great job! Next one:", Game.screenSize.width / 2 - 225, 100);
            g.drawString("Press 'S' key to move backward ", Game.screenSize.width / 2 - 375, 200);
        } else if (Game.tutStep == 4) {
            g.setColor(Color.yellow);
            g.drawString("Be careful!", Game.screenSize.width / 2 - 100, 100);
            g.setColor(Color.white);
            g.drawString("Going backward is slower than going forward!", Game.screenSize.width / 2 - 550, 200);
        } else if (Game.tutStep == 5) {
            g.setColor(Color.white);
            g.drawString("Well done soldier!", Game.screenSize.width / 2 - 200, 100);
            g.drawString("Now use left-mouse to fire anywhere you want", Game.screenSize.width / 2 - 550, 200);
        } else if (Game.tutStep == 6) {
            g.setColor(Color.yellow);
            g.drawString("You are prepared!", Game.screenSize.width / 2 - 200, 100);
            g.setColor(Color.white);
            g.drawString("You can press Esc to exit and join the battle!", Game.screenSize.width / 2 - 550, 200);
        }
    }

    public void aboutRender(Graphics g) {
        if (Music.tut1.isActive()) {
            Game.Mcheck = false;
            Music.tut1.stop();
        }
        if (!Game.Mcheck) {
            Game.Mcheck = true;
            Music.menu.play();
            Music.menu.loop();
        }

        Graphics2D g2d = (Graphics2D) g;
        if (space == null) {
            space = getImage("/textures/space1.jpg");
        }
        g2d.drawImage(space, 0, 0, Game.screenSize.width, Game.screenSize.height, this);
        //basic setting for font
        Font font1 = new Font("Arial", Font.BOLD, 50);
        g.setFont(font1);

        g.setColor(Color.lightGray);
        g.drawString("Software Architecture: Design & Implementation", Game.screenSize.width / 2 - 575, 50);

        g.setColor(Color.yellow);
        g.drawString("Developers", Game.screenSize.width / 2 - 130, 100);
        g.drawString("Lecturer", Game.screenSize.width / 2 - 100, 400);
        g.drawString("Advisor", Game.screenSize.width / 2 - 90, 600);

        g.setColor(Color.white);
        g.drawString("Nguyen Hoan Tien           s3515589", Game.screenSize.width / 2 - 410, 200);
        g.drawString("Nguyen Quang Hai           s3517235", Game.screenSize.width / 2 - 410, 300);
        g.drawString("Vladimir Mariano", Game.screenSize.width / 2 - 210, 500);
        g.drawString("Nguyen Dang Tuan", Game.screenSize.width / 2 - 240, 700);

        g2d.draw(AboutBack);
        Font font2 = new Font("arial", Font.BOLD, 25);

        Hcount++;
        if (Hcount >= 5) {
            Hc = 0;
        }
        if (Game.Hover == Game.HOVER.ABOUTBACK) {
            Hcount = 0;
            if (Hc == 1) {
                Hcheck = true;
            } else {
                Hcheck = false;
            }
            Hc = 1;
            if (!Hcheck && Hc == 1) {
                Hcheck = true;
                Music.hover.play();
            }
            g.setColor(Color.white);
            g.drawString("BACK", AboutBack.x + 18, AboutBack.y + 43);
        } else {
            g.setColor(Color.yellow);
            g.drawString("BACK", AboutBack.x + 18, AboutBack.y + 43);
        }
    }
}
