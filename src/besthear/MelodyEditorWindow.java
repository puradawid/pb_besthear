/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package besthear;

import besthear.sound.Playback;
import besthear.sound.TextNoteParsed;
import besthear.sound.TextNotePlayer;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Dialog;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.TextArea;

/**
 *
 * @author Dawid
 */
public class MelodyEditorWindow extends Dialog implements Bindable{
    
    @BXML
    PushButton playMelodyButton;
    
    public static TextArea melodyNotes;
    
    @BXML
    TextArea melodyInputText;
    
    public static TextNotePlayer tnp;
    protected Playback p;
    
    @Override
    public void initialize(Map<String, Object> map, URL url, Resources rsrcs) {
        AudioFormat format = new AudioFormat(16384.0f, 16, 1, true, true);
        p = new Playback(format, null);
        tnp = new TextNotePlayer(p);
        melodyNotes = melodyInputText;
        playMelodyButton.getButtonPressListeners().add(new ButtonPressListener(){
            @Override
            public void buttonPressed(Button button) {
                String input = MelodyEditorWindow.melodyNotes.getText();
                MelodyEditorWindow.tnp.changeSequency(TextNoteParsed.parseWholeString(input));
                (new Thread(MelodyEditorWindow.tnp)).start();
//                try {
//                 Thread.sleep(tnp.timeOfPlay());
//                } catch (InterruptedException ex) {
//                }
            }
            
        });
    }
    
}
