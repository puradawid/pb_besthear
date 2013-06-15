/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package besthear.sound;

import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import javax.sound.sampled.Line.Info;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Port;
import javax.sound.sampled.TargetDataLine;

/**
 *
 * @author Dawid
 */
public class Capture extends Thread{

    TargetDataLine dl;
    BlockingQueue<byte[]> samplesLine = new LinkedBlockingQueue<>(10);
    boolean pleaseStop = true;
    int avail = 0;

    public Capture() {
        
    }

    @Override
    public void run() {
        AudioFormat format = new AudioFormat(16384.0f, 16, 1, true, true);
        TargetDataLine dl = null;
        try {
            dl = AudioSystem.getTargetDataLine(format);
        } catch (Exception e) {
        }
        byte[] data;
        int i = 100;
        try {
            dl.open();
            dl.start();
            while (pleaseStop) {
                data = new byte[2048];
                dl.read(data, 0, data.length);
                samplesLine.put(data);
                avail++;
            }
            dl.stop();
            dl.close();
        } catch (Exception e) {
            System.out.println("There is error with sampling");
            System.out.println(e.getMessage());
        }
    }
    
    public byte[] takeSample()
    {
        avail--;
        return samplesLine.poll();
    }
    
    public int avaible()
    {
        return avail;
    }
    
    public void consume()
    {
        byte[] data = new byte[2048];
        data = samplesLine.poll();
        if(data == null) return;
        avail--;
        int max = 0, min = 0;
        double avg = 0.0;
        int sample = 0;
        for (int i = 0; i < data.length - 1; i += 2) {
            sample = data[i] *( 1<< 8) + data[i + 1];
            if (max < sample) {
                max = sample;
            }
            if (min > sample) {
                min = sample;
            }
            avg += sample;
        }
        avg = avg / (data.length / 2);
        System.out.println("Amplituda wynosi: " + (max - min));
        System.out.println("Srednia wynosi: " + avg);
    }
    
    public void stopCapturing()
    {
        pleaseStop = false;
    }
}
