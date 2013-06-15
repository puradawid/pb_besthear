/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package besthear.sound;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Dawid
 */
public class TextNoteParsed extends TextNote {
    
    public static List<TextNote> parseWholeString(String input)
    {
        List<TextNote> result = new LinkedList<>();
        String[] list = input.split(";");
        for(String inputs : list)
            result.add(new TextNoteParsed(inputs));
        
        return result;
    }
    
    //assume it is incoming in {%freq, %time}
    public TextNoteParsed(String statement)
    {
        String regex = "\\{|,|\\}";
        String[] parsing = statement.split(regex);
        frequency = Integer.parseInt(parsing[1]);
        time = Integer.parseInt(parsing[2]);
    }
}
