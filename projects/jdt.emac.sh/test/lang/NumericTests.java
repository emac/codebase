/*
 * Created on 2011-11-20
 */

package lang;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author emac
 */
public class NumericTests {

    @Test
    public void testOverflow() {
        int year = 11;
        int month = 11;
        int day = 11;

        long bookingId = (((10 * 100 + year) * 100 + month) * 100 + day) * 10000;
        assertFalse(101111110001L == bookingId + 1);

        bookingId = (((10L * 100 + year) * 100 + month) * 100 + day) * 10000;
        assertTrue(101111110001L == bookingId + 1);

        assertFalse(new Integer(1) == new Integer(1));
    }
}
