/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package besthear.sound;

/**
 * Simple class to represent a frequency note - note includes frequency and time
 * of existing.
 * @author Dawid
 */
public abstract class TextNote {
    
    protected int frequency;
    protected int time;
    
    /**
     * Returns note's frequency.
     * @return frequency in int
     */
    public int getFrequency()
    {
        return frequency;
    }
    
    /**
     * Returns time of note.
     * @return time in int
     */
    public int getTime()
    {
        return time;
    }
    
}
