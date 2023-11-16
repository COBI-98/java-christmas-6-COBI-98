package christmas.domain.calendar;

import static christmas.message.ErrorMessages.INVALID_DATE_RANGE;

public record Day(int day) {

    private static int MIN_DAY = 1;
    private static int EVEN_MONTHS_MAX_DAY = 31;

    public Day {
        validateRangeFromDay(day);
    }

    private void validateRangeFromDay(int day) {
        if (day < MIN_DAY || day > EVEN_MONTHS_MAX_DAY) {
            throw new IllegalArgumentException(INVALID_DATE_RANGE.getMessage());
        }
    }

}
