package service;

import model.DateRange;

import java.time.LocalDateTime;

public interface HolidayTimeService {

    LocalDateTime addBusinessTime(DateRange dateRange, LocalDateTime time, long duration);

}
