/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package besthear;

import java.net.URL;
import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Dialog;
import org.apache.pivot.wtk.Slider;
import org.apache.pivot.wtk.SliderValueListener;
import org.apache.pivot.wtk.Window;

/**
 *
 * @author Dawid
 */
public class SettingsWindow extends Dialog implements Bindable {
    
    @BXML
    protected Slider amplitudeSlider;

    @Override
    public void initialize(Map<String, Object> map, URL url, Resources rsrcs) {
        amplitudeSlider.setValue((int)Math.round(BestHear.amplitude * 100));
        amplitudeSlider.getSliderValueListeners().add(new SliderValueListener(){

            @Override
            public void valueChanged(Slider slider, int i) {
                BestHear.amplitude = i/100.0;
            }
    });
    }
    
    
}
