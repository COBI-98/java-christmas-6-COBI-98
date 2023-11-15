package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.calendar.Year;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class YearTest {

    @DisplayName("constructor() : 방문년도 생성")
    @Test
    void month_constructor_success() throws Exception{
        //given
        Year visitYear = selectYear();

        //when //then
        assertThat(visitYear.year()).isEqualTo(2023);
    }

    private static Year selectYear() {
        return new Year(2023);
    }
}