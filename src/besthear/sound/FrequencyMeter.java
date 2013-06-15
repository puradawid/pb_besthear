/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package besthear.sound;

import besthear.tools.FFT;

/**
 * Static instance to measure frequency o sound.
 * @author Dawid
 */
public class FrequencyMeter {
    
    Capture c; //capture sound
    
    public FrequencyMeter()
    {
        c = new Capture();
    }
    
    public void start()
    {
        c.start();
    }
    
    public int getFrequency()
    {
        byte[] sample = c.takeSample();
        if(sample == null) return -1;
        FFT transform = new FFT(c.takeSample(), 2, 16384.0);
        transform.calculate();
        return transform.maxFreq();
    }
    
    public void stop()
    {
        c.stopCapturing();
    }
}
