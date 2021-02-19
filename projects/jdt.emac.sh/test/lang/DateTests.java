package lang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author Emac
 * @date 2021/2/19
 */
public class DateTests {

    @Test
    public void testSimpleDateFormat() {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2020);
        calendar.set(Calendar.MONDAY, 11);
        calendar.set(Calendar.DAY_OF_MONTH, 29);
        Assertions.assertNotEquals("2020-12-29", sdf.format(calendar.getTime()));
        Assertions.assertEquals("2021-12-29", sdf.format(calendar.getTime()));
    }
}
