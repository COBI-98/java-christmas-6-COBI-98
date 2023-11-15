package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.menu.MenuType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuTypeTest {

    @DisplayName("toString() : 메뉴 타입 요구사항 재정의")
    @Test
    void menu_type_to_string_success() throws Exception{
        //given
        MenuType buttonMushroomSoup = MenuType.BUTTON_MUSHROOM_SOUP;

        //when //then
        assertThat(buttonMushroomSoup.toString()).isEqualTo("양송이수프(6,000)");
    }
}