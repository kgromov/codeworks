package task41;

import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by konstantin on 01.04.2020.
 */
public class PowerTest {
    @Test
    public void compute() throws Exception {
        Assert.assertEquals(new Pair<>(2, 4), Power.compute(16));
        Assert.assertEquals(new Pair<>(3, 4), Power.compute(81));
        Assert.assertEquals(new Pair<>(15, 2), Power.compute(225));
        Assert.assertEquals(new Pair<>(2, 12), Power.compute(4096));
        Assert.assertEquals(null, Power.compute(0));
        Assert.assertEquals(new Pair<>(1, 1), Power.compute(1));
        Assert.assertEquals(new Pair<>(2, 2), Power.compute(4));
    }

}