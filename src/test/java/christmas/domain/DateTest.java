package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DateTest {

    @DisplayName("constructor() : 방문 날짜를 구현한다.")
    @Test
    void date_constructor_success() throws Exception{
        //given
        Date date = new Date(25);

        //when //then
        assertThat(date.toString()).isEqualTo("12월 25일");
    }
}