package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.calendar.Day;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DayTest {


    @DisplayName("constructor() : 방문일 생성")
    @Test
    void day_constructor_success() throws Exception {
        //given
        Day visitDay = selectDay();

        //when //then
        assertThat(visitDay.day()).isEqualTo(31);
    }

    @DisplayName("validateRangeFromDay() : 1이상 31 이하의 숫자가 아닌 경우")
    @ParameterizedTest
    @ValueSource(ints = {0, 32})
    void validateRangeFromDay_fail(int visitDay) throws Exception {
        //given
        String exceptionMessage = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";

        //when //then
        assertThatThrownBy(() -> new Day(visitDay))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(exceptionMessage);
    }

    private static Day selectDay() {
        return new Day(31);
    }
}