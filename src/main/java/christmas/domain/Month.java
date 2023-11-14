package christmas.domain;

import static christmas.message.ErrorMessages.INVALID_DAY_RANGE;

public record Month(int month) {

    private static int MIN_MONTH = 1;
    private static int MAX_MONTH = 12;

    public Month {
        validateRangeFromMonth(month);
    }

    private void validateRangeFromMonth(int day) {
        if (day < MIN_MONTH || day > MAX_MONTH){
            throw new IllegalArgumentException(INVALID_DAY_RANGE.getMessage());
        }
    }
}
