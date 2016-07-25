package Input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import game.Game;
import game.Menu;
public class Mouse implements MouseListener, MouseMotionListener {

    private static int mouseX = -1;
    private static int mouseY = -1;
    private static int mouseB = -1;

    public static int getX() {
        //System.out.println ("X: " + mouseX);
        return mouseX;
    }

    public static int getY() {
        //System.out.println ("Y: " + mouseY);
        return mouseY;
    }

    public static int getButton() {
        return mouseB;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
          if (Game.State == Game.STATE.MENU) {
            int mx = e.getX();
            int my = e.getY();

            //handle menu clicking
            if (Game.State == Game.STATE.MENU) {
                //Click on Play Button
                if (mx >= Menu.width - 50 && mx <= Menu.width + 200) {
                    if (my >= 150 && my <= 250) {
                        Game.State = Game.STATE.PLAY;
                    }
                }
                //Click on Quit Button
                if (mx >= Menu.width - 50 && mx <= Menu.width + 200) {
                    if (my >= 450 && my <= 550) {
                        System.exit(0);
                    }
                }
            }
        }

        //handle clicking Difficulties buttons 
        else if (Game.State == Game.STATE.PLAY) {
            int mx = e.getX();
            int my = e.getY();
            //Click on Back Button
            if (mx >= Menu.BackX && mx <= Menu.BackX+ Menu.BackW) {
                if (my >= Menu.BackY && my <= Menu.BackY+Menu.BackH) {
                    Game.State = Game.STATE.MENU;
                }
            }
            //CLick on Start Button
            if (mx >= Menu.StartX && mx <= Menu.StartX+ Menu.StartW) {
                if (my >= Menu.StartY && my <= Menu.StartY+Menu.StartH) {
                    Game.State = Game.STATE.GAME;
                }
            }
           
        } else {
            mouseB = e.getButton();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseB = -1;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

}
