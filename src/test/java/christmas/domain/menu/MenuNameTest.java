package christmas.domain.menu;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.menu.MenuName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MenuNameTest {

    @DisplayName("constructor() : 메뉴 이름 일치")
    @ParameterizedTest
    @ValueSource(strings = {"티본스테이크", "바비큐립", "초코케이크", "제로콜라"})
    void menu_name_constructor_success(String name) throws Exception {
        //given
        MenuName menuName = new MenuName(name);

        //when //then
        assertThat(menuName).isNotNull();
    }

    @DisplayName("validateRestaurantContainsFromMenuName() : 유효하지 않는 메뉴인 경우")
    @ParameterizedTest
    @ValueSource(strings = {"양송이슾", "양송이스프", "앙송이수프", "T본스테이크"})
    void validateRestaurantContainsFromMenuName_fail(String menuName) throws Exception {
        //given
        String exceptionMessage = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

        //when //then
        assertThatThrownBy(() -> new MenuName(menuName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(exceptionMessage);
    }
}