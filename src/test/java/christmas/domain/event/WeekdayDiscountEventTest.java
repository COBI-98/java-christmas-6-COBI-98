package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.calendar.Date;
import christmas.domain.menu.Menu;
import christmas.domain.menu.Menus;
import christmas.domain.calendar.Order;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class WeekdayDiscountEventTest {

    @DisplayName("isApplicable() : 평일이 아닌 경우")
    @ParameterizedTest
    @ValueSource(ints = {8, 9, 15, 16})
    void WeekdayDiscountEvent_isApplicable_fail(int day) throws Exception{
        //given
        Event weekdayDiscountEvent = new WeekdayDiscountEvent();
        Date visitOfDate = new Date(day);

        //when
        boolean hasEventEffectiveDate = weekdayDiscountEvent.isApplicable(visitOfDate);

        // then
        assertThat(hasEventEffectiveDate).isFalse();
    }

    @DisplayName("calculateDiscount() : 크리스마스 평일 디저트 할인 금액 확인")
    @ParameterizedTest
    @CsvSource({"티본스테이크,2,초코케이크,3,6069", "초코케이크,1,아이스크림,4,10115"})
    void WeekdayDiscountEvent_calculateDiscount_success(
            String menu1, String quantity1,
            String menu2, String quantity2, int discountAmount) throws Exception{
        //given
        int visitWeekDay = 4;
        Event weekdayDiscountEvent = new WeekdayDiscountEvent();
        Date visitOfDate = new Date(visitWeekDay);
        Order order = createOrder(menu1, quantity1, menu2, quantity2);

        //when
        int discount = weekdayDiscountEvent.calculateDiscount(visitOfDate, order);

        // then
        assertThat(discount).isEqualTo(discountAmount);

    }

    private Order createOrder(String menu1, String quantity1, String menu2, String quantity2) {
        Menus menus = new Menus(List.of(new Menu(menu1, quantity1),
                new Menu(menu2, quantity2)));

        return new Order(menus);
    }
}