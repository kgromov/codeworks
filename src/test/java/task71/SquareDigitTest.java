package task71;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by konstantin on 10.10.2021.
 */
public class SquareDigitTest {

    @Test
    public void squareDigits() {
        assertEquals(811181, new SquareDigit().squareDigits2(9119));
        assertEquals(0, new SquareDigit().squareDigits(0));
    }

}