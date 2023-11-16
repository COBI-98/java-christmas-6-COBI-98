package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.calendar.Date;
import christmas.domain.calendar.Order;
import christmas.domain.menu.Menu;
import christmas.domain.menu.Menus;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class SpecialDiscountEventTest {

    @DisplayName("isApplicable() : 휴일이 아닌 경우")
    @ParameterizedTest
    @ValueSource(ints = {18, 26})
    void SpecialDiscountEvent_isApplicable_fail(int day) throws Exception {
        //given
        Event specialDiscountEvent = new SpecialDiscountEvent();
        Date visitOfDate = new Date(day);

        //when
        boolean hasEventEffectiveDate = specialDiscountEvent.isApplicable(visitOfDate);

        // then
        assertThat(hasEventEffectiveDate).isFalse();
    }

    @DisplayName("calculateDiscount() : 공휴일 할인 확인")
    @ParameterizedTest
    @CsvSource({"18,티본스테이크,2,초코케이크,3,1000", "25,초코케이크,1,아이스크림,4,1000"})
    void SpecialDiscountEvent_calculateDiscount_success(
            int visitHoliday, String menu1, String quantity1,
            String menu2, String quantity2, int discountAmount) throws Exception {
        //given
        Event specialDiscountEvent = new SpecialDiscountEvent();
        Date visitOfDate = new Date(visitHoliday);
        Order order = createOrder(menu1, quantity1, menu2, quantity2);

        //when
        int discount = specialDiscountEvent.calculateDiscount(visitOfDate, order);

        // then
        assertThat(discount).isEqualTo(discountAmount);

    }

    private Order createOrder(String menu1, String quantity1, String menu2, String quantity2) {
        Menus menus = new Menus(List.of(new Menu(menu1, quantity1),
                new Menu(menu2, quantity2)));

        return new Order(menus);
    }
}