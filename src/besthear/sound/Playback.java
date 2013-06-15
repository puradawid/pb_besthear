/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package besthear.sound;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

/**
 *
 * @author Dawid
 */
public class Playback implements Runnable {
    
    SourceDataLine sdl;
    boolean runned = false;
    byte[] stream;
    AudioFormat af;
    Thread t;
    
    public Playback(AudioFormat af, byte[] stream)
    {
        try {
            sdl = AudioSystem.getSourceDataLine(af);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Playback.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.stream = stream;
        this.af = af;
    }

    public void startSound()
    {
        runned = true;
        try {
            sdl.open();
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Playback.class.getName()).log(Level.SEVERE, null, ex);
        }
        sdl.start();
        t = new Thread(this);
        t.start();
    }
    
    public void stopSound()
    {
        runned = false;
        sdl.stop();
        sdl.close();
    }
    
    public void changeStream(byte[] stream)
    {
        this.stream = stream;
    }
    
    public AudioFormat getFormat()
    {
        return af;
    }
    
    @Override
    public void run() {
        while(runned)
        {
            sdl.write(stream, 0, stream.length);
        }
    }
}
