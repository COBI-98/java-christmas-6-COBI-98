package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.menu.MenuQuantity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MenuQuantityTest {

    @DisplayName("constructor() : 메뉴 개수 생성")
    @ParameterizedTest
    @ValueSource(strings = {"1", "5"})
    void menu_quantity_success(String quantity) throws Exception {
        //given
        MenuQuantity menuQuantity = new MenuQuantity(quantity);

        //when //then
        assertThat(menuQuantity).isNotNull();
    }

    @DisplayName("validateEmptyFromMenuQuantity() : 수량을 체크하지 않은 경우")
    @Test
    void validateEmptyFromMenuQuantity_fail() throws Exception {
        //given
        String quantity = "";
        String exceptionMessage = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

        //when //then
        assertThatThrownBy(() -> new MenuQuantity(quantity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(exceptionMessage);
    }

    @DisplayName("validateCharacterFromMenuQuantity() : 수량에 문자가 들어가는 경우")
    @ParameterizedTest
    @ValueSource(strings = {" ", "e", "1e", "1dollar", "#"})
    void validateCharacterFromMenuQuantity_fail(String quantity) throws Exception {
        //given
        String exceptionMessage = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

        //when //then
        assertThatThrownBy(() -> new MenuQuantity(quantity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(exceptionMessage);
    }

    @DisplayName("validateRangeFromMenuQuantity() : 1이상 20이하의 수량을 체크하지 않는 경우")
    @ParameterizedTest
    @ValueSource(strings = {"0", "21"})
    void validateRangeFromMenuQuantity_fail(String quantity) throws Exception {
        String exceptionMessage = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

        //when //then
        assertThatThrownBy(() -> new MenuQuantity(quantity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(exceptionMessage);
    }
}