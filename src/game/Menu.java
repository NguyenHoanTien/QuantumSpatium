
package game;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.net.URL;


public class Menu extends Applet {
    private Image space = null;
    private int button_extraX1 = 60, button_extraX2 = 85;
    private int button_extraY = 65;
    public static int width = (Game.screenSize.width / 2) - 150;
    public static int height = (Game.screenSize.height / 2);
    
      //Main menu buttons
    public static Rectangle Button1 = new Rectangle(Game.screenSize.width - 210 ,Game.screenSize.height/2 - 90,120,50);
    public static Rectangle Button2 = new Rectangle(Game.screenSize.width - 235 ,Game.screenSize.height/2 + 30,160,50);
    public static Rectangle Button3 = new Rectangle(Game.screenSize.width - 210 ,Game.screenSize.height/2 + 150,120,50);
   
    //play menu
    public Rectangle MenuBox = new Rectangle(width-65,50,400, 500);
    //PLAY MENU Back Button input
    public static int BackX = width - 25;
    public static int BackY = 450;
    public static int BackW = 125;
    public static int BackH = 75;
    private Rectangle Back = new Rectangle(BackX,BackY,BackW,BackH);
    
     //PLAY MENU Start Button input
    public static int StartX = BackX + 200;
    public static int StartY = BackY;
    public static int StartW = 125;
    public static int StartH = BackH;
    private Rectangle Start = new Rectangle(StartX,StartY,StartW,StartH);
    
    //Game over menu
    public Rectangle Box = new Rectangle(width-65,height - 125,400,250);
    public static int OverY = height + 25;
    private Rectangle OverBack = new Rectangle(BackX,OverY,BackW,BackH);
    private Rectangle Restart = new Rectangle(StartX,OverY,StartW,StartH);
    
    public Image getImage(String path){
        Image tempImage = null;
        try{
            URL imageURL = Menu.class.getResource(path);
            tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
        }
        catch (Exception e){
        }
        return tempImage;
    }
    //main menu Reder method
    public void mainRender(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        if (space == null){
            space = getImage("/textures/space1.jpg");
        }
        g2d.drawImage(space,0,0,Game.screenSize.width,Game.screenSize.height,this);
        //basic setting for font
        Font font1 = new Font(".VnBahamasBH",Font.BOLD, 100);
        g.setFont(font1);
        g.setColor(Color.white);
        
        //draw the title
        g.drawString(Game.title,width - 75,Game.screenSize.height/2 - 200);
        //draw buttons
        Font font2 = new Font(".VnRevueH",Font.BOLD,35);
        g.setFont(font2);
        g.setColor(Color.yellow);
        g.drawString("PLAY",Game.screenSize.width - 200, Game.screenSize.height/2 - 50);
        g.drawString("ABOUT",Game.screenSize.width - 225, Game.screenSize.height/2 + 70 );
        g.drawString("QUIT",Game.screenSize.width - 200, Game.screenSize.height/2 + 190);
        g.setColor(Color.blue);
//        g2d.draw(Button1);
//        g2d.draw(Button2);
//        g2d.draw(Button3);
    }
    
    //render difficulty menu after click PLAY
    public void playRender(Graphics g, int counter ){
        Graphics2D g2d = (Graphics2D) g;
        counter = 5 - counter;
        String counterStr = Integer.toString(counter);
        //basic setting for font
        Font font1 = new Font("calibri",Font.BOLD, 150);
        g.setFont(font1);
        g.setColor(Color.white);
        g.drawString(counterStr,Game.screenSize.width/2 - 45,Game.screenSize.height/2);
//        g2d.draw(MenuBox);
//        g.setColor(Color.gray);
//        g.fillRect(width-65,50,400, 500);
//        Font font1 = new Font("arial",Font.BOLD, 50);
//        Font font2 = new Font("calibri",Font.BOLD,25);
//        g.setFont(font1);
//        g.setColor(Color.red);
//        g.drawString("HOW TO PLAY",width - 45,100);
//        g.setFont(font2);
//        
//        ////////
//        g.setColor(Color.blue);
//        g2d.draw(Back);
//        g.setColor(Color.darkGray);
//        g.fillRect(BackX,BackY,BackW,BackH);
//        g.setColor(Color.red);
//        g.drawString("BACK",BackX + 35,BackY + 45);
//        
//        g.setColor(Color.blue);
//        g2d.draw(Start);
//        g.setColor(Color.red);
//        g.fillRect(StartX,StartY,StartW,StartH);
//        g.setColor(Color.black);
//        g.drawString("START",StartX + 30,StartY + 45);
        
        //draw buttons
//        Font font2 = new Font("calibri",Font.BOLD,35);
//        g.setFont(font2);
//        g.drawString("EASY",Button1.x + button_extraX2 , Button1.y + button_extraY);
//        g2d.draw(Button1);
//        g.drawString("NORMAL",Button2.x + button_extraX1, Button2.y + button_extraY);
//        g2d.draw(Button2);
//        g.drawString("HARD",Button3.x + button_extraX2, Button3.y + button_extraY);
//        g2d.draw(Button3);
//        g.setColor(Color.red);
//        g.drawString("BACK",Button4.x + button_extraX2, Button4.y + button_extraY);
//        g2d.draw(Button4);
    }
    public void pauseRender(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.blue);
        g2d.draw(Box);
        g.setColor(Color.lightGray);
        g.fillRect(width-65,height - 125,400,250);
        Font font1 = new Font("arial",Font.BOLD, 50);
        Font font2 = new Font("calibri",Font.BOLD,25);
        g.setFont(font1);
        g.setColor(Color.red);
        g.drawString("PAUSED",width + 35,height - 35);
        g.setFont(font2);
        
        ////////
        g.setColor(Color.blue);
        g2d.draw(Restart);
        g2d.draw(OverBack);
        
        g.setColor(Color.darkGray);
        g.fillRect(BackX,OverY,BackW,BackH);
        g.setColor(Color.white);
        g.drawString("QUIT",BackX + 35,OverY + 45);
        
        g.setColor(Color.darkGray);
        g.fillRect(StartX,OverY,StartW,StartH);
        g.setColor(Color.white);
        g.drawString("RESUME",StartX + 25,OverY + 45);
    }
    
    public void overRender(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.blue);
        g2d.draw(Box);
        g.setColor(Color.lightGray);
        g.fillRect(width-65,height - 125,400,250);
        Font font1 = new Font("arial",Font.BOLD, 50);
        Font font2 = new Font("calibri",Font.BOLD,25);
        g.setFont(font1);
        g.setColor(Color.red);
        g.drawString("GAME OVER",width - 20,height - 35);
        g.setFont(font2);
        
        ////////
        g.setColor(Color.blue);
        g2d.draw(Restart);
        g2d.draw(OverBack);
        
        g.setColor(Color.darkGray);
        g.fillRect(BackX,OverY,BackW,BackH);
        g.setColor(Color.white);
        g.drawString("QUIT",BackX + 35,OverY + 45);
        
        g.setColor(Color.darkGray);
        g.fillRect(StartX,OverY,StartW,StartH);
        g.setColor(Color.white);
        g.drawString("RESTART",StartX + 18,OverY + 45);
    }
}
