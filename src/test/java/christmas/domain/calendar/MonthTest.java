package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.calendar.Month;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MonthTest {

    @DisplayName("constructor() : 방문월 생성")
    @Test
    void month_constructor_success() throws Exception {
        //given
        Month visitMonth = selectMonth();

        //when //then
        assertThat(visitMonth.month()).isEqualTo(12);
    }

    @DisplayName("validateRangeFromMonth() : 1이상 12 이하의 숫자가 아닌 경우")
    @ParameterizedTest
    @ValueSource(ints = {0, 13})
    void validateRangeFromMonth_fail(int visitMonth) throws Exception {
        //given
        String exceptionMessage = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";

        //when //then
        assertThatThrownBy(() -> new Month(visitMonth))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(exceptionMessage);
    }

    private static Month selectMonth() {
        return new Month(12);
    }
}