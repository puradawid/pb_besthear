/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package besthear.sound.tests;

import besthear.sound.Playback;
import besthear.sound.TextNote;
import besthear.sound.TextNoteParsed;
import besthear.sound.TextNotePlayer;
import besthear.sound.TextNoteSimple;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
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
public class TextNotePlayerTest {
    
    public TextNotePlayerTest() {
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
    public void test()
    {
        Playback pb = new Playback(new AudioFormat(16384.0f, 16, 1, true, true), null);
        TextNotePlayer tnp = new TextNotePlayer(pb);
        tnp.changeSequency(TextNoteParsed.parseWholeString("{440,200};{600,400};{450,200}{440,200};{600,400};{450,200}{440,200};{500,400};{450,200};{350,200}"));
        (new Thread(tnp)).start();
        try {
            Thread.sleep(tnp.timeOfPlay());
        } catch (InterruptedException ex) {
            Logger.getLogger(TextNotePlayerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}