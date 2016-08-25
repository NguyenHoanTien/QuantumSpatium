
package Audio;

import javax.sound.sampled.*;


public class Music {
    private Clip clip;
    // Change file name to match yours, of course

    public static Music menu = new Music("/audio/menu.wav");
    public static Music hover = new Music("/audio/Menuclick.wav");
    public static Music tut1 = new Music("/audio/tutorial.wav");
    public static Music Game = new Music("/audio/Space-Cube.mp3");
    public static Music count = new Music("/audio/countdown.wav");
    public static Music hover1 = new Music("/audio/hover.wav");
    public static Music shoot = new Music("/audio/shooting.wav");
    public static Music shoot1 = new Music("/audio/testshoot.wav");
    public static Music exp1 = new Music("/audio/exp1.wav");
    public static Music exp = new Music("/audio/exp.wav");
    public static Music bip = new Music("/audio/bip.wav");
    
    public Music (String fileName) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(Music.class.getResource(fileName));
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }
 
    public void play() {
        try {
            if (clip != null) {
                new Thread() {
                    public void run() {
                        synchronized (clip) {
                            clip.stop();
                            clip.setFramePosition(0);
                            clip.start();
                        }
                    }
                }.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
    public void stop(){
        if(clip == null) return;
        clip.stop();
    }
     
    public void loop() {
        try {
            if (clip != null) {
                new Thread() {
                    public void run() {
                        synchronized (clip) {
                            clip.stop();
                            clip.setFramePosition(0);
                            clip.loop(Clip.LOOP_CONTINUOUSLY);
                        }
                    }
                }.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
    public boolean isActive(){
        return clip.isActive();
    }

}
