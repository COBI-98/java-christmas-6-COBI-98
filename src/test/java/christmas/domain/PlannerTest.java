package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import christmas.domain.event.Event;
import java.sql.SQLOutput;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PlannerTest {

    @DisplayName("constructor() : 이벤트 최소 주문 금액 10000원을 넘지 않는 경우 ")
    @ParameterizedTest
    @CsvSource({"타파스,1,제로콜라,1", "양송이수프,1,제로콜라,1"})
    void planner_constructor_fail(
            String menu1, String quantity1, String menu2, String quantity2) throws Exception{
        //given
        Date visitDay = new Date(3);
        Order order = createOrder(menu1, quantity1, menu2, quantity2);
        Planner planner = new Planner(visitDay, order);

        //when //then
        assertThat(planner.getEvents()).isEmpty();
    }

    @DisplayName("constructor() : 이벤트 할인 혜택 적용 ")
    @Test
    void planner_constructor_success() throws Exception{
        //given
        Order order = createOrder();
        Date visitDay = new Date(3);
        Planner planner = new Planner(visitDay, order);
        int expectedAmount = 31246;

        //when //then
        planner.calculateBenefits(visitDay, order);

        assertThat(planner.getAfterAmount()).isEqualTo(expectedAmount);
    }

    private Order createOrder(String menu1, String quantity1, String menu2, String quantity2) {
        Menus menus = new Menus(List.of(new Menu(menu1, quantity1),
                new Menu(menu2, quantity2)));

        return new Order(menus);
    }

    private Order createOrder() {
        Menus menus = new Menus(List.of(new Menu("티본스테이크", "1"),
                new Menu("바비큐립", "1"),
                new Menu("초코케이크", "2"),
                new Menu("제로콜라", "1")));

        return new Order(menus);
    }
}