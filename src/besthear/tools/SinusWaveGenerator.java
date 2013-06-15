/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package besthear.tools;

/**
 *
 * @author Dawid
 */
public class SinusWaveGenerator {
    int sampleNumber;
    int amp;
    int frequency;
    int[] samples;
    byte[] soundSamples;
    public SinusWaveGenerator(int sampleNumber, int amp, int frequency)
    {
        this.sampleNumber = sampleNumber;
        this.amp = amp;
        this.frequency = frequency;
    }
    
    public byte[] produceSamples(int outFreq, double ampFactor)
    {
        double alterFreq = (double)frequency/(double)outFreq;
        int[] samples = new int[frequency];
        for(int i = 0; i < frequency; i++)
            samples[i] = (int)Math.round(Math.sin((i*2*Math.PI)/alterFreq) *((amp*ampFactor)/2));
        return splitIntsToBytes(samples);
    }
    
    protected byte[] splitIntsToBytes(int[] array)
    {
        byte[] result = new byte[array.length * 2];
        for(int i=0; i < array.length; i++)
        {
            result[2*i] = (byte)((array[i] & 0xFF00) >> 8);
            result[2*i + 1] = (byte)((array[i] & 0x0000FF));
        }
        return result;
    }       
     
}
