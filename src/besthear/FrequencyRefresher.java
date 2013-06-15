/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package besthear;

import besthear.sound.FrequencyMeter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.pivot.wtk.Label;

/**
 *
 * @author Dawid
 */
public class FrequencyRefresher extends Thread {
    
    boolean work = true;
    
    Label consumer;
    
    FrequencyMeter producer;
    
    MainWindow mw;
    
    public FrequencyRefresher(Label consumer, FrequencyMeter producer, MainWindow w)
    {
        this.consumer = consumer;
        this.producer = producer;
        this.mw = w;
    }
    
    @Override
    public void run()
    {
        int value = producer.getFrequency();
        if(value != -1)
            consumer.setText(Integer.toString(value));
    }
    
    public void stopWork()
    {
        work = false;
    }
}
