package java8;

import org.junit.Assert;
import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

import static java.time.ZoneOffset.UTC;

/**
 * @author Emac
 * @since 2015-11-29
 */
public class TimeTests {

    @Test
    public void testLocal() {
        // Get the current date and time
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("Current DateTime: " + currentTime);

        LocalDate date1 = currentTime.toLocalDate();
        System.out.println("date1: " + date1);

        Month month = currentTime.getMonth();
        int day = currentTime.getDayOfMonth();
        int seconds = currentTime.getSecond();

        System.out.println("Month: " + month.getValue() + "day: " + day + "seconds: " + seconds);

        LocalDateTime date2 = currentTime.withDayOfMonth(10).withYear(2012);
        System.out.println("date2: " + date2);

        //12 december 2014
        LocalDate date3 = LocalDate.of(2014, Month.DECEMBER, 12);
        System.out.println("date3: " + date3);

        //22 hour 15 minutes
        LocalTime date4 = LocalTime.of(22, 15);
        System.out.println("date4: " + date4);

        //parse a string
        LocalTime date5 = LocalTime.parse("20:15:30");
        System.out.println("date5: " + date5);
    }

    @Test
    public void testZoned() {
        // Get the current date and time
        ZonedDateTime date1 = ZonedDateTime.parse("2007-12-03T10:15:30+05:30[Asia/Karachi]");
        System.out.println("date1: " + date1);

        System.out.println(date1.toLocalDateTime());
        System.out.println(date1.toOffsetDateTime());

        ZoneId id = ZoneId.of("Europe/Paris");
        System.out.println("ZoneId: " + id);

        ZoneId currentZone = ZoneId.systemDefault();
        System.out.println("CurrentZone: " + currentZone);

        System.out.println(date1.withZoneSameLocal(ZoneId.systemDefault()));
        System.out.println(date1.withZoneSameInstant(ZoneId.systemDefault()));
    }

    @Test
    public void testPeriod() {
        //Get the current date
        LocalDate date1 = LocalDate.now();
        System.out.println("Current date: " + date1);

        //add 1 month to the current date
        LocalDate date2 = date1.plus(1, ChronoUnit.MONTHS);
        System.out.println("Next month: " + date2);

        Period period = Period.between(date2, date1);
        System.out.println("Period: " + period);
    }

    @Test
    public void testDuration() {
        // LocalTime
        LocalTime time1 = LocalTime.now();
        Duration twoHours = Duration.ofHours(2);

        LocalTime time2 = time1.plus(twoHours);
        Duration duration = Duration.between(time1, time2);

        System.out.println("Duration: " + duration.toHours());

        // Format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHH");
        time1 = LocalTime.parse("17081614", formatter);
        duration = Duration.between(time1, LocalTime.now());

        System.out.println("Duration: " + duration.toHours());
    }

    @Test
    public void testAdjusters() {
        //Get the current date
        LocalDate date1 = LocalDate.now();
        System.out.println("Current date: " + date1);

        //get the next tuesday
        LocalDate nextTuesday = date1.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
        System.out.println("Next Tuesday on : " + nextTuesday);

        //get the second saturday of next month
        LocalDate firstInYear = LocalDate.of(date1.getYear(), date1.getMonth(), 1);
        LocalDate secondSaturday = firstInYear.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY)).with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
        System.out.println("Second Saturday on : " + secondSaturday);
    }

    @Test
    public void testBackwardCompatability() {
        //Get the current date
        Date currentDate = new Date();
        System.out.println("Current date: " + currentDate);

        //Get the instant of current date in terms of milliseconds
        Instant now = currentDate.toInstant();
        System.out.println(now);
        ZoneId currentZone = ZoneId.systemDefault();

        LocalDateTime localDateTime = LocalDateTime.ofInstant(now, currentZone);
        System.out.println("Local date: " + localDateTime);

        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(now, currentZone);
        System.out.println("Zoned date: " + zonedDateTime);
    }

    @Test
    public void testOffsetDateTime() {
        Instant instant = Instant.ofEpochMilli(1494518400000L);
        OffsetDateTime date = OffsetDateTime.ofInstant(instant, ZoneId.systemDefault());
        OffsetDateTime date2 = OffsetDateTime.ofInstant(instant, UTC);
        System.out.println(date);
        System.out.println(date2);
        Assert.assertNotEquals(date, date2);
    }
}
