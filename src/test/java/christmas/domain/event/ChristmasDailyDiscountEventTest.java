package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import christmas.domain.Date;
import christmas.domain.Menu;
import christmas.domain.Menus;
import christmas.domain.Order;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class ChristmasDailyDiscountEventTest {

    @DisplayName("isApplicable() : 유효한 이벤트 날짜가 아닌 경우")
    @ParameterizedTest
    @ValueSource(ints = {26, 27, 30})
    void ChristmasDailyDiscountEvent_isApplicable_fail(int day) throws Exception{
        //given
        Event christmasDailyDiscountEvent = new ChristmasDailyDiscountEvent();
        Date visitOfDate = new Date(day);

        //when 
        boolean hasEventEffectiveDate = christmasDailyDiscountEvent.isApplicable(visitOfDate);

        // then
        assertThat(hasEventEffectiveDate).isFalse();
    }

    @DisplayName("calculateDiscount() : 크리스마스 할인 금액 확인")
    @ParameterizedTest
    @CsvSource({"1,1000", "20,2900", "25,3400"})
    void ChristmasDailyDiscountEvent_calculateDiscount_success(int day, int discountAmount) throws Exception{
        //given
        Event christmasDailyDiscountEvent = new ChristmasDailyDiscountEvent();
        Date visitOfDate = new Date(day);
        Order order = createOrder();

        //when
        int discount = christmasDailyDiscountEvent.calculateDiscount(visitOfDate, order);

        // then
        assertThat(discount).isEqualTo(discountAmount);

    }

    private Order createOrder() {
        Menus menus = new Menus(List.of(new Menu("티본스테이크", "1"),
                new Menu("바비큐립", "1"),
                new Menu("초코케이크", "2"),
                new Menu("제로콜라", "1")));

        return new Order(menus);
    }
}