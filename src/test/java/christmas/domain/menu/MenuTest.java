package christmas.domain.menu;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.menu.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuTest {

    @DisplayName("constructor() : 주문 메뉴를 구현한다.")
    @Test
    void menu_constructor_success() throws Exception {
        //given
        Menu menu = new Menu("양송이수프", "1");

        //when //then
        assertThat(menu.toString()).isEqualTo("양송이수프 1개");
    }
}