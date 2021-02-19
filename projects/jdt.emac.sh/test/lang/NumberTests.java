/*
 * Created on 2011-11-20
 */

package lang;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author emac
 */
public class NumberTests {

    @Test
    public void testOverflow() {
        int year = 11;
        int month = 11;
        int day = 11;

        long bookingId = (((10 * 100 + year) * 100 + month) * 100 + day) * 10000;
        assertFalse(101111110001L == bookingId + 1);

        bookingId = (((10L * 100 + year) * 100 + month) * 100 + day) * 10000;
        assertTrue(101111110001L == bookingId + 1);
    }

    @Test
    public void testEquals() {
        assertTrue((Integer) 100 == (Integer) 100);
        assertFalse((Integer) 200 == (Integer) 200);
        assertFalse(new Integer(100) == new Integer(100));
    }

    @Test
    public void testTuple() {
        assertThrows(NullPointerException.class, () -> {
            Integer foo = null;
            System.out.println((int) foo);
        });
    }
}
