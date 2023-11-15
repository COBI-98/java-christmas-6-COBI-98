package christmas.domain;

import christmas.domain.calendar.Order;
import christmas.domain.menu.Menu;
import christmas.domain.menu.Menus;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class OrderTest {

    @DisplayName("createTotalOrderMoney() : 총 주문 금액")
    @ParameterizedTest
    @CsvSource({"양송이수프,6000,3,티본스테이크,55000,1",
            "타파스,5500,10,제로콜라,3000,1 "})
    void order_createTotalOrderMoney_success(String menu1, int amount1, String quantity1,
            String menu2, int amount2, String quantity2) throws Exception{
        //given
        Menus menus = new Menus(List.of(new Menu(menu1, quantity1),
                new Menu(menu2, quantity2)));

        int menuTotalAmount1 = amount1 * Integer.parseInt(quantity1);
        int menuTotalAmount2 = amount2 * Integer.parseInt(quantity2);
        int sum = menuTotalAmount1 + menuTotalAmount2;

        //when
        Order order = new Order(menus);

        // then
        Assertions.assertThat(order.getBeforeMoney()).isEqualTo(sum);
    }

}