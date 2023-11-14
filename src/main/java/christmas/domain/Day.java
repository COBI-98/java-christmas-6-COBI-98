package christmas.domain;

public record Day(int day) {

    private static int MIN_DAY = 1;
    private static int EVEN_MONTHS_MAX_DAY = 31;

    public Day {
        validateRangeFromDay(day);
    }

    private void validateRangeFromDay(int day) {
        if (day < MIN_DAY || day > EVEN_MONTHS_MAX_DAY){
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

}
