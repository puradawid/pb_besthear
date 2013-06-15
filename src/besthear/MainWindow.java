/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package besthear;

import besthear.sound.Playback;
import besthear.tools.SinusWaveGenerator;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Alert;
import org.apache.pivot.wtk.ApplicationContext;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Dialog;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.Menu;
import org.apache.pivot.wtk.MenuBar;
import org.apache.pivot.wtk.MessageType;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.Window;

/**
 *
 * @author Dawid
 */
public class MainWindow extends Window implements Bindable{
    
    public static Window instance;
    
    @BXML
    PushButton storeFrequency;
    
    @BXML
    PushButton playFrequency;
    
    @BXML
    Label freq;
    
    @BXML
    Menu.Item settingsButton;
    
    @BXML
    Menu.Item melodyEditorButton;
    
    int frequency = 440;
    
    boolean playing = false;
    
    Playback pb;
    
    SinusWaveGenerator swg;
    
    FrequencyRefresher fr;
    
    @Override
    public void initialize(Map<String, Object> map, URL url, Resources rsrcs) {
        instance = this;
        this.setSize(100, 200);
        fr = new FrequencyRefresher(freq, BestHear.fm, this);
        
        //settingsButton.setEnabled(true);
        
        StyleDictionary s = freq.getStyles();
        
        settingsButton.getButtonPressListeners().add(new ButtonPressListener(){
            @Override
            public void buttonPressed(Button button)
            {
              BestHear.settingsWindow.open(MainWindow.instance);
            }
        });
        melodyEditorButton.getButtonPressListeners().add(new ButtonPressListener(){
            @Override
            public void buttonPressed(Button button)
            {
              BestHear.melodyEditorWindow.open(MainWindow.instance);
            }
        });
        storeFrequency.getButtonPressListeners().add(new ButtonPressListener(){
            @Override
            public void buttonPressed(Button button)
            {
                int newFreq = Integer.parseInt(freq.getText());
                frequency = newFreq;
                System.out.println("Change frequency to " + frequency);
            }
        });
        final AudioFormat format = new AudioFormat(16384.0f, 16, 1, true, true);
        
        playFrequency.getButtonPressListeners().add(new ButtonPressListener(){
            @Override
            public void buttonPressed(Button button)
            {
                if(swg == null)
                {
                swg = new SinusWaveGenerator(0, Short.MAX_VALUE, 16384);
                byte[] stream = swg.produceSamples(frequency, BestHear.amplitude);
                pb = new Playback(format, stream);
                pb.startSound();
                } else
                {
                pb.stopSound();
                swg = null;
                }
            }
        });
        
        ApplicationContext.scheduleRecurringCallback(fr, 100);
         
   }
}
  
