/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package besthear.tools.tests;

import besthear.tools.SinusWaveGenerator;
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
public class SinusWaveGeneratorTest {
    
    public SinusWaveGeneratorTest() {
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
        SinusWaveGenerator swg = new SinusWaveGenerator(0, Integer.MAX_VALUE, 16384);
        byte[] result = swg.produceSamples(400, 1);
    }
}