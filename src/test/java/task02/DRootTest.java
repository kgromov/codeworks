package task02;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by konstantin on 02.02.2020.
 */
public class DRootTest {
    @Test
    public void Tests() {
        assertEquals( "Nope!" , 7, DRoot.digital_root(16));
        assertEquals( "Nope!" , 6, DRoot.digital_root(456));
        assertEquals( "Nope!" , 9, DRoot.digital_root(324));
    }

}