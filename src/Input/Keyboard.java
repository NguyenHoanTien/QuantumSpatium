package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import game.Game;

public class Keyboard implements KeyListener {

    private boolean[] keys;
    public boolean up, down, left, right;

    public Keyboard() {
        keys = new boolean[120];        //  256 = keycode for a single key on the keyboard
    }

    public void update() {
        up = keys[KeyEvent.VK_W];        // set key to send signal (press W to go up)
        down = keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_S && Game.tutStep == 3) {
                Game.tutStep++;
            }
        keys[e.getKeyCode()] = true;            // if key code = true => press button
        // checking !
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if ((Game.State == Game.STATE.GAME || Game.State == Game.STATE.RUNTUT) && e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            Game.prevState = Game.State;
            Game.State = Game.STATE.PAUSE;
        } else if (Game.State == Game.STATE.RUNTUT) {
            if (e.getKeyCode() == KeyEvent.VK_A && Game.tutStep == 1) {
                Game.a_released = true;
                if (Game.a_released && Game.d_released) {
                    Game.tutStep++;
                }
            } else if (e.getKeyCode() == KeyEvent.VK_D && Game.tutStep == 1) {
                Game.d_released = true;
                if (Game.a_released && Game.d_released) {
                    Game.tutStep++;
                }
            } else if (e.getKeyCode() == KeyEvent.VK_W && Game.tutStep == 2) {
                Game.tutStep++;
            } else if (e.getKeyCode() == KeyEvent.VK_S && Game.tutStep == 4) {
                Game.tutStep++;
            }
        }
        keys[e.getKeyCode()] = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
