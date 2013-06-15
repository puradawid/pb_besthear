/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package besthear;

import besthear.sound.FrequencyMeter;
import java.awt.Color;
import java.awt.Font;
import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.collections.Map;
import org.apache.pivot.wtk.Application;
import org.apache.pivot.wtk.DesktopApplicationContext;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.HorizontalAlignment;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.VerticalAlignment;
import org.apache.pivot.wtk.Window;

/**
 *
 * @author Dawid
 */
public class BestHear implements Application {

    /**
     * @param args the command line arguments
     */
    private Window window;
    public static FrequencyMeter fm;
    
    public static void main(String[] args) {
        DesktopApplicationContext.main(BestHear.class, args);
    }

    @Override
    public void startup(Display dspl, Map<String, String> map) throws Exception {
        BXMLSerializer bxmlSerializer = new BXMLSerializer();
        fm = new FrequencyMeter();
        window = (Window)bxmlSerializer.readObject(BestHear.class, "firstWindow.bxml");
        window.open(dspl);
        fm.start();
    }

    @Override
    public boolean shutdown(boolean bln) throws Exception {
        fm.stop();
        return false;
    }

    @Override
    public void suspend() throws Exception {
    }

    @Override
    public void resume() throws Exception {
    }
}