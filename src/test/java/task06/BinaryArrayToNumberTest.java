package task06;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class BinaryArrayToNumberTest {
    @Test
    public void convertBinaryArrayToInt() throws Exception {

        assertEquals(1, BinaryArrayToNumber.convertBinaryArrayToInt(Arrays.asList(0,0,0,1)));
        assertEquals(15, BinaryArrayToNumber.convertBinaryArrayToInt(Arrays.asList(1,1,1,1)));
        assertEquals(6, BinaryArrayToNumber.convertBinaryArrayToInt(Arrays.asList(0,1,1,0)));
        assertEquals(9, BinaryArrayToNumber.convertBinaryArrayToInt(Arrays.asList(1,0,0,1)));


    }
}