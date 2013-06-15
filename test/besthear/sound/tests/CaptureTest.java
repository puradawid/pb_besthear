/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package besthear.sound.tests;

import besthear.sound.Capture;
import besthear.tools.FFT;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dawid
 */
public class CaptureTest {
    
    public CaptureTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void creation() throws Exception
    {
        Capture c = new Capture();
        c.start();
        Thread.sleep(10);
        while(true)
        {
            if(c.avaible() == 0)
            {
                System.out.println("Nie mam probek!");
                continue;
            }
            byte[] samples = c.takeSample();
            if(samples == null) continue;
            FFT trans = new FFT(samples, 2, 16384.0);
            c.takeSample();
            double [] tr = trans.calculate();
            int freq = trans.maxFreq();
            if(freq > 0)
            {
                System.out.println("Najlepsza częstotliwość dźwięku:" + freq);
                //c.consume();
            }
        }
    }
}