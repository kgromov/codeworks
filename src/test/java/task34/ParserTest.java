package task34;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by konstantin on 28.03.2020.
 */
public class ParserTest {
    @Test
    public void parseInt() throws Exception {
        assertEquals(2_000_000 , BufferParser.parseInt("two million"));
        assertEquals(1 , BufferParser.parseInt("one"));
        assertEquals(200 , BufferParser.parseInt("two hundred"));
        assertEquals(46 , BufferParser.parseInt("forty-six"));
        assertEquals(20 , BufferParser.parseInt("twenty"));
        assertEquals(124 , BufferParser.parseInt("one hundred and twenty-four"));
        assertEquals(246 , BufferParser.parseInt("two hundred forty-six"));
        assertEquals(2788 , BufferParser.parseInt("two thousand seven hundred eighty-eight"));
        assertEquals(1337 , BufferParser.parseInt("one thousand three hundred thirty-seven"));
        assertEquals(31348 , BufferParser.parseInt("thirty-one thousand three hundred forty-eight"));
        assertEquals(26359 , BufferParser.parseInt("twenty-six thousand three hundred fifty-nine"));
        assertEquals(783919 , BufferParser.parseInt("seven hundred eighty-three thousand nine hundred and nineteen"));
    }

}