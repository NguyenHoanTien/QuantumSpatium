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
        int mx = e.getX();
        int my = e.getY();
        if (Game.State == Game.STATE.MENU) {
            //handle menu clicking
            if (Game.State == Game.STATE.MENU) {
                //Click on Play Button
                if (mx >= Menu.Button1.x && mx <= Menu.Button1.x + 120) {
                    if (my >= Menu.Button1.y && my <= Menu.Button1.y + 50) {
                        Game.State = Game.STATE.PLAY;
                    }
                }
                //Click on Quit Button
                if (mx >= Menu.Button3.x && mx <= Menu.Button3.x + 120) {
                    if (my >= Menu.Button3.y && my <= Menu.Button3.y + 50) {
                        System.exit(0);
                    }
                }
            }
        } //handle clicking PLAY MENU buttons 
//        else if (Game.State == Game.STATE.PLAY) {
//            //Click on Back Button
//            if (mx >= Menu.BackX && mx <= Menu.BackX + Menu.BackW) {
//                if (my >= Menu.BackY && my <= Menu.BackY + Menu.BackH) {
//                    Game.State = Game.STATE.MENU;
//                }
//            }
//            //CLick on Start Button
//            if (mx >= Menu.StartX && mx <= Menu.StartX + Menu.StartW) {
//                if (my >= Menu.StartY && my <= Menu.StartY + Menu.StartH) {
//                    Game.State = Game.STATE.GAME;
//                }
//            }
//
//        } //handle clicking GAME OVER MENU buttons
        else if (Game.State == Game.STATE.OVER || Game.State == Game.STATE.PAUSE) {
            //Click on BACK button
            if (mx >= Menu.BackX && mx <= Menu.BackX + Menu.BackW) {
                if (my >= Menu.OverY && my <= Menu.OverY + Menu.BackH) {
                    Game.State = Game.STATE.MENU;
                }
            }
            //CLick on Restart Button
            if (mx >= Menu.StartX && mx <= Menu.StartX + Menu.StartW) {
                if (my >= Menu.OverY && my <= Menu.OverY + Menu.StartH) {
                    if (Game.State == Game.STATE.OVER)
                        Game.State = Game.STATE.PLAY;
                    else
                        Game.State = Game.STATE.GAME;
                }
            }
        } else if (Game.State == Game.STATE.DEAD) {
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
