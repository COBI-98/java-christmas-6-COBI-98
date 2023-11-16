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

class FreeGiftEventTest {

    @DisplayName("calculateDiscount() : 총주문금액 12만 원 이하일 때 증정메뉴 없음")
    @ParameterizedTest
    @CsvSource({"티본스테이크,1,초코케이크,1", "티본스테이크,1,바비큐립,1"})
    void FreeGiftEvent_calculateDiscount_fail(
            String menu1, String quantity1, String menu2, String quantity2) throws Exception {
        //given
        int visitDay = 5;
        int expectedDiscount = 0;
        Event freeGiftEvent = new FreeGiftEvent();
        Date visitOfDate = new Date(visitDay);
        Order order = createOrder(menu1, quantity1, menu2, quantity2);

        //when
        int discount = freeGiftEvent.calculateDiscount(visitOfDate, order);

        // then
        assertThat(discount).isEqualTo(expectedDiscount);
    }


    @DisplayName("calculateDiscount() : 총주문금액 12만 원 이상일 때 할인")
    @ParameterizedTest
    @CsvSource({"티본스테이크,3,초코케이크,3", "티본스테이크,1,바비큐립,2"})
    void FreeGiftEvent_calculateDiscount_success(
            String menu1, String quantity1, String menu2, String quantity2) throws Exception {
        //given
        int visitDay = 5;
        int expectedDiscount = 25_000;
        Event freeGiftEvent = new FreeGiftEvent();
        Date visitOfDate = new Date(visitDay);
        Order order = createOrder(menu1, quantity1, menu2, quantity2);

        //when
        int discount = freeGiftEvent.calculateDiscount(visitOfDate, order);

        // then
        assertThat(discount).isEqualTo(expectedDiscount);
    }

    private Order createOrder(String menu1, String quantity1, String menu2, String quantity2) {
        Menus menus = new Menus(List.of(new Menu(menu1, quantity1),
                new Menu(menu2, quantity2)));

        return new Order(menus);
    }
}