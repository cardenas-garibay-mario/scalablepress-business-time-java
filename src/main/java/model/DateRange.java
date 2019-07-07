package model;

import java.time.LocalDateTime;

public class DateRange {

    private final LocalDateTime start;
    private final LocalDateTime end;

    public DateRange(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }
}
