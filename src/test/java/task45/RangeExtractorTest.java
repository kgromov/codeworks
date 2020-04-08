package task45;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by konstantin on 08.04.2020.
 */
public class RangeExtractorTest {
    @Test
    public void toRange() throws Exception {
        assertEquals("-6,-3-1,3-5,7-11,14,15,17-20",
                RangeExtractor.toRange(new int[] {-6,-3,-2,-1,0,1,3,4,5,7,8,9,10,11,14,15,17,18,19,20}));
        assertEquals("-3--1,2,10,15,16,18-20",
                RangeExtractor.toRange(new int[] {-3,-2,-1,2,10,15,16,18,19,20}));
    }

}