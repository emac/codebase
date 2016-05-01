/*
 * Created on 2011-11-20
 */

package lang;


import org.junit.Assert;
import org.junit.Test;

/**
 * @author emac
 */
public class NumericTests
{

    @Test
    public void testOverflow()
    {
        int year = 11;
        int month = 11;
        int day = 11;

        long bookingId = (((10 * 100 + year) * 100 + month) * 100 + day) * 10000;
        Assert.assertFalse(101111110001L == bookingId + 1);

        bookingId = (((10L * 100 + year) * 100 + month) * 100 + day) * 10000;
        Assert.assertTrue(101111110001L == bookingId + 1);

        Assert.assertFalse(new Integer(1) == new Integer(1));
    }

}
