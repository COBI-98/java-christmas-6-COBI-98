package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.calendar.Date;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DateTest {

    @DisplayName("constructor() : 방문 날짜를 구현한다.")
    @Test
    void date_constructor_success() throws Exception{
        //given
        Date date = new Date(25);

        //when //then
        assertThat(date.toString()).isEqualTo("12월 25일");
    }

    @DisplayName("createDateTimeFormat() : 날짜 형식을 구현한다.")
    @Test
    void createDateTimeFormat_success() throws Exception{
        //given
        Date date = new Date(25);

        //when //then
        assertThat(date.createDateTimeFormat()).isEqualTo("2023-12-25");
    }

    @DisplayName("isWeekend() : 금요일, 토요일인 경우")
    @ParameterizedTest
    @ValueSource(ints = {8,9})
    void date_isWeekend_success(int day) throws Exception{
        //given
        Date date = new Date(day);

        //when //then
        assertThat(date.isWeekend()).isTrue();
    }

    @DisplayName("isWeekend() : 평일인 경우")
    @ParameterizedTest
    @ValueSource(ints = {10,11,12,13,14})
    void date_isWeekend_fail(int day) throws Exception{
        //given
        Date date = new Date(day);

        //when //then
        assertThat(date.isWeekend()).isFalse();
    }
}