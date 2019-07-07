package service;

import model.DateRange;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class HolidayTimeServiceImplTest {

    private HolidayTimeService holidayTimeService;
    private DateRange dateRange;

    @Before
    public void setUp() {
        holidayTimeService = new HolidayTimeServiceImpl();
        LocalDateTime startDate = LocalDateTime.parse("2019-12-24T21:00:00");
        LocalDateTime endDate = LocalDateTime.parse("2019-12-25T21:00:00");
        dateRange = new DateRange(startDate, endDate);
    }


    @Test
    public void shouldReturnSelectedTimePlusDurationWhenIsNotHoliday() {
        LocalDateTime time = LocalDateTime.parse("2019-12-01T00:00:00");
        LocalDateTime expectedDate = LocalDateTime.parse("2019-12-01T01:00:00");
        LocalDateTime result = holidayTimeService.addBusinessTime(dateRange, time, 60 * 60);
        assertEquals(expectedDate, result);
    }

    @Test
    public void shouldReturnHolidayEndPlusDurationWhenSelectedTimeIsStartingHoliday() {
        LocalDateTime time = LocalDateTime.parse("2019-12-24T21:00:00");
        LocalDateTime expectedDate = LocalDateTime.parse("2019-12-25T21:00:01");
        LocalDateTime result = holidayTimeService.addBusinessTime(dateRange, time, 1);
        assertEquals(expectedDate, result);
    }

    @Test
    public void shouldReturnHolidayEndPlusRemainingDurationWhenSelectedTimeStartBeforeOrEqualHolidayStar() {
        LocalDateTime time = LocalDateTime.parse("2019-12-24T20:30:00");
        LocalDateTime expectedDate = LocalDateTime.parse("2019-12-25T21:30:00");
        LocalDateTime result = holidayTimeService.addBusinessTime(dateRange, time, 60 * 60);
        assertEquals(expectedDate, result);
    }

    @Test
    public void shouldReturnHolidayEndPlusDurationWhenSelectedTimeIsBetweenHoliday() {
        LocalDateTime time = LocalDateTime.parse("2019-12-24T20:30:00");
        LocalDateTime expectedDate = LocalDateTime.parse("2019-12-25T21:30:00");
        LocalDateTime result = holidayTimeService.addBusinessTime(dateRange, time, 60 * 60);
        assertEquals(expectedDate, result);
    }

    @Test
    public void shouldReturnHolidayStartMinusDurationTimeWhenSelectedTimeIsBetweenHoliday() {
        LocalDateTime time = LocalDateTime.parse("2019-12-24T20:30:00");
        LocalDateTime expectedDate = LocalDateTime.parse("2019-12-25T21:30:00");
        LocalDateTime result = holidayTimeService.addBusinessTime(dateRange, time, 60 * 60);
        assertEquals(expectedDate, result);
    }

}