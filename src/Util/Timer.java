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
public class Timer {

    private long tStart, tEnd, tDelta;
    private double elapsedSeconds;
    private int duration;
    public long getStartTime(){
        return tStart;
    }
    
    public long getEndTime(){
        return tEnd;
    }
    
    public int getDuration(){
        return duration =  (int)elapsedSeconds;
    }
    
    private void calculateTime(){
        tDelta = tEnd - tStart;
        elapsedSeconds = tDelta /1000.0;
    }
    public void start(){
        tStart = System.currentTimeMillis();
    }
    
    public void stop(){
        tEnd = System.currentTimeMillis();
        calculateTime();
    }
}
