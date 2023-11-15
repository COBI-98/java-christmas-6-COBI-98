package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import christmas.domain.Date;
import christmas.domain.Menu;
import christmas.domain.Menus;
import christmas.domain.Order;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class WeekendDiscountEventTest {

    @DisplayName("isApplicable() : 주말이 아닌 경우")
    @ParameterizedTest
    @ValueSource(ints = {10, 11, 12, 13, 14})
    void WeekendDiscountEvent_isApplicable_fail(int day) throws Exception{
        //given
        Event weekendDiscountEvent = new WeekendDiscountEvent();
        Date visitOfDate = new Date(day);

        //when
        boolean hasEventEffectiveDate = weekendDiscountEvent.isApplicable(visitOfDate);

        // then
        assertThat(hasEventEffectiveDate).isFalse();
    }

    @DisplayName("calculateDiscount() : 크리스마스 주말 메인 할인 금액 확인")
    @ParameterizedTest
    @CsvSource({"티본스테이크,2,초코케이크,3,4046", "티본스테이크,3,바비큐립,1,8092"})
    void WeekendDiscountEvent_calculateDiscount_success(
            String menu1, String quantity1,
            String menu2, String quantity2, int discountAmount) throws Exception{
        //given
        int visitWeekDay = 4;
        Event weekendDiscountEvent = new WeekendDiscountEvent();
        Date visitOfDate = new Date(visitWeekDay);
        Order order = createOrder(menu1, quantity1, menu2, quantity2);

        //when
        int discount = weekendDiscountEvent.calculateDiscount(visitOfDate, order);

        // then
        assertThat(discount).isEqualTo(discountAmount);

    }

    private Order createOrder(String menu1, String quantity1, String menu2, String quantity2) {
        Menus menus = new Menus(List.of(new Menu(menu1, quantity1),
                new Menu(menu2, quantity2)));

        return new Order(menus);
    }
}