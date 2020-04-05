package task43;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by konstantin on 05.04.2020.
 */
public class PaginationHelperTest {
    private PaginationHelper<Character> helper;

    @Before
    public void setUp() throws Exception {
        helper = new PaginationHelper<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f'), 4);
    }

    @Test
    public void itemCount() throws Exception {
        Assert.assertEquals(6 , helper.itemCount());
    }

    @Test
    public void pageCount() throws Exception {
        Assert.assertEquals(2 , helper.pageCount());
    }

    @Test
    public void pageItemCount() throws Exception {
        Assert.assertEquals(4 , helper.pageItemCount(0));
        Assert.assertEquals(2 , helper.pageItemCount(1));
        Assert.assertEquals(-1 , helper.pageItemCount(2));
    }

    @Test
    public void pageIndex() throws Exception {
        Assert.assertEquals(1 , helper.pageIndex(5));
        Assert.assertEquals(0 , helper.pageIndex(2));
        Assert.assertEquals(-1 , helper.pageIndex(20));
        Assert.assertEquals(-1 , helper.pageIndex(-10));
    }

}