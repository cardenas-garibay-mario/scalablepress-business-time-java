package service;

import model.DateRange;

import java.time.Duration;
import java.time.LocalDateTime;

public class HolidayTimeServiceImpl implements HolidayTimeService {

    public LocalDateTime addBusinessTime(DateRange holiday, LocalDateTime time, long duration) {

        if (isTimeBetweenHoliday(holiday, time)) {
            if (duration > 0) {
                return holiday.getEnd().plusSeconds(duration);
            } else {
                return holiday.getStart().plusSeconds(duration);
            }
        }

        if (isAddedDurationTimeBetweenHoliday(holiday, time, duration)) {
            long timeBeforeToStart = Duration.between(time, holiday.getStart()).getSeconds();
            long timeToAddToEnd = duration - timeBeforeToStart;
            return holiday.getEnd().plusSeconds(timeToAddToEnd);
        }

        return time.plusSeconds(duration);
    }

    private boolean isTimeBetweenHoliday(DateRange holiday, LocalDateTime time) {
        return time.isAfter(holiday.getStart())
                || time.isEqual(holiday.getStart())
                && time.isBefore(holiday.getEnd())
                || time.isEqual(holiday.getEnd());
    }

    private boolean isAddedDurationTimeBetweenHoliday(DateRange holiday, LocalDateTime time, long durationInSeconds) {
        return (time.isBefore(holiday.getStart())
                || time.isEqual(holiday.getStart()))
                && time.plusSeconds(durationInSeconds).isAfter(holiday.getStart());
    }

}
