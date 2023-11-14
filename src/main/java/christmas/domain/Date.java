package christmas.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Date {
    private static final int VISIT_YEAR = 2023;
    private static final int VISIT_MONTH = 12;
    private Year year;
    private Month month;
    private Day day;

    public Date(int day) {
        this.year = new Year(VISIT_YEAR);
        this.month = new Month(VISIT_MONTH);
        this.day = new Day(day);
    }

    @Override
    public String toString() {
        return String.format("%s월 %s일", month.month(), day.day());
    }
}
