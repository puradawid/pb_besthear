/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package besthear.sound;

import besthear.tools.SinusWaveGenerator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dawid
 */
public class TextNotePlayer implements Runnable {
    /** describes a sounds and order in melody */
    List<TextNote> notes = new LinkedList<>();
    Playback playbackDevice;
    SinusWaveGenerator swg;
    double ampFactor = 1.0;
    
    public TextNotePlayer(Playback device)
    {
        playbackDevice = device;
        swg = new SinusWaveGenerator(0, Short.MAX_VALUE, (int)device.getFormat().getSampleRate());
    }
    
    public void addToSequency(TextNote tn)
    {
        notes.add(tn);
    }
    
    public void changeSequency(List<TextNote> tnl)
    {
        notes = tnl;
    }
    
    @Override
    public void run() {
        for(TextNote tn : notes)
        {
            playbackDevice.changeStream(
                    swg.produceSamples(tn.getFrequency(), ampFactor));
            playbackDevice.startSound();
            try {
                Thread.sleep(tn.getTime());
            } catch (InterruptedException ex) {
                Logger.getLogger(TextNotePlayer.class.getName()).log(Level.SEVERE, null, ex);
            }
            playbackDevice.stopSound();
        }
    }
    
    public long timeOfPlay()
    {
        long sum = 0;
        for(TextNote t : notes)
            sum+=t.getTime();
        return sum;
    }
}
