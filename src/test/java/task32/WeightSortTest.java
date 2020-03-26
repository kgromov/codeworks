package task32;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by konstantin on 26.03.2020.
 */
public class WeightSortTest {
    @Test
    public void orderWeight() throws Exception {
        System.out.println("****** Basic Tests ******");
        assertEquals("2000 103 123 4444 99",
                WeightSort.orderWeightBubbleSort("103 123 4444 99 2000"));
        assertEquals("11 11 2000 10003 22 123 1234000 44444444 9999",
                WeightSort.orderWeightBubbleSort("2000 10003 1234000 44444444 9999 11 11 22 123"));
    }

}