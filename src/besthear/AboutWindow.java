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
import org.apache.pivot.wtk.TextArea;

/**
 *
 * @author Dawid
 */
public class AboutWindow extends Dialog implements Bindable {
    @BXML
    TextArea text;

    @Override
    public void initialize(Map<String, Object> map, URL url, Resources rsrcs) {
        text.setText("BestHear is frequency meter and generator\n Author: Dawid Pura\n Created for the third project in KCK\n Ownership: Politechnika Bialostocka\n Bialystok 2013" );
    }
    
    
}
