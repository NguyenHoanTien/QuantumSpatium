
package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class Menu {
    private int button_extraX1 = 60, button_extraX2 = 85;
    private int button_extraY = 65;
    public static int width = (Game.screenSize.width / 2) - 150;
    public Rectangle MenuBox = new Rectangle(width-65,50,400, 500);
    public Rectangle Button1 = new Rectangle(width ,150,250,100);
    public Rectangle Button2 = new Rectangle(width ,300,250,100);
    public Rectangle Button3 = new Rectangle(width ,450,250,100);
    public Rectangle Button4 = new Rectangle(width ,600,250,100);
    
    //Back Button input
    public static int BackX = width - 25;
    public static int BackY = 450;
    public static int BackW = 125;
    public static int BackH = 75;
    private Rectangle Back = new Rectangle(BackX,BackY,BackW,BackH);
    
     //Back Button input
    public static int StartX = BackX + 200;
    public static int StartY = BackY;
    public static int StartW = 125;
    public static int StartH = BackH;
    private Rectangle Start = new Rectangle(StartX,StartY,StartW,StartH);
    
    //main menu Reder method
    public void mainRender(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        //basic setting for font
        Font font1 = new Font("arial",Font.BOLD, 50);
        g.setFont(font1);
        g.setColor(Color.white);
        
        //draw the title
        g.drawString(Game.title,width,100);
        //draw buttons
        Font font2 = new Font("calibri",Font.BOLD,35);
        g.setFont(font2);
        g.setColor(Color.yellow);
        g.drawString("PLAY",Button1.x + button_extraX2 , Button1.y + button_extraY);
        g.drawString("SETTING",Button2.x + button_extraX1, Button2.y + button_extraY);
        g.drawString("QUIT",Button3.x + button_extraX2, Button3.y + button_extraY);
        g.setColor(Color.blue);
        g2d.draw(Button1);
        g2d.draw(Button2);
        g2d.draw(Button3);
    }
    
    //render difficulty menu after click PLAY
    public void playRender(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        //basic setting for font
        
        g2d.draw(MenuBox);
        g.setColor(Color.gray);
        g.fillRect(width-65,50,400, 500);
        Font font1 = new Font("arial",Font.BOLD, 50);
        Font font2 = new Font("calibri",Font.BOLD,25);
        g.setFont(font1);
        g.setColor(Color.black);
         //draw the title
        g.drawString(Game.title,width,100);
        g.setFont(font2);
        g.setColor(Color.red);
        g.drawString("HOW TO PLAY",width + 55,150);
        ////////
        
        ////////
        g2d.draw(Back);
        g.setColor(Color.darkGray);
        g.fillRect(BackX,BackY,BackW,BackH);
        g.setColor(Color.red);
        g.drawString("BACK",BackX + 35,BackY + 45);
        
        g2d.draw(Start);
        g.setColor(Color.red);
        g.fillRect(StartX,StartY,StartW,StartH);
        g.setColor(Color.black);
        g.drawString("START",StartX + 30,StartY + 45);
        
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
}
