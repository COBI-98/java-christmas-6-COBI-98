package christmas.domain.calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class Date {
    private static final int VISIT_YEAR = 2023;
    private static final int VISIT_MONTH = 12;
    private Year year;
    private Month month;
    private Day day;
    private String dayFormat;

    public Date(int day) {
        this.year = new Year(VISIT_YEAR);
        this.month = new Month(VISIT_MONTH);
        this.day = new Day(day);
        this.dayFormat = createVisitingDayFormat();
    }

    public String createVisitingDayFormat() {
        LocalDate visitDay = createDateTimeFormat();
        DayOfWeek dayOfWeek = visitDay.getDayOfWeek();
        return dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREA);
    }

    public boolean isWeekend() {
        LocalDate visitDay = createDateTimeFormat();
        DayOfWeek dayOfWeek = visitDay.getDayOfWeek();
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }

    public boolean isSunday() {
        LocalDate visitDay = createDateTimeFormat();
        DayOfWeek dayOfWeek = visitDay.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SUNDAY;
    }

    public LocalDate createDateTimeFormat() {
        return LocalDate.of(getYear(), getMonth(), getDay());
    }

    @Override
    public String toString() {
        return String.format("%s월 %s일", month.month(), day.day());
    }

    public int getYear() {
        return year.year();
    }

    public int getMonth() {
        return month.month();
    }

    public int getDay() {
        return day.day();
    }

    public String getDayFormat() {
        return dayFormat;
    }
}
