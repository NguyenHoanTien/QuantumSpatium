/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

/**
 *
 * @author kronos
 */
public class Combo {

    private int combo = 0;
    private Timer counter = new Timer();
    private int Comboduration;

    void Combo() {
    }
    
    public int getCombo(){
        return combo;
    }
    public void update() {
        this.counter.start();
        combo++;
    }

    public void check() {
        this.counter.stop();
        Comboduration =counter.getDuration();
        System.out.println(Comboduration);
        if(Comboduration > 3)
            this.combo = 0;    
    }
}
