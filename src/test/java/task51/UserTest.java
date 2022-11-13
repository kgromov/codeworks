package task51;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by konstantin on 30.04.2020.
 */
public class UserTest {
    @Test
    public void incProgress() throws Exception {
       User user = new User();
       user.rank = -2;
       user.incProgress(-3);
       assertEquals(-2, user.rank);
    }

}