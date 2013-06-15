/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package besthear.sound.tests;

import besthear.sound.TextNote;
import besthear.sound.TextNoteParsed;
import java.util.List;
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
public class TestNoteParsedTest {
    
    public TestNoteParsedTest() {
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
       String input = "{100,200}";
       TextNoteParsed tn = new TextNoteParsed(input);
       
       String input1 = "{100,300};{200,13}";
       List<TextNote> ts = TextNoteParsed.parseWholeString(input1);
   }
}