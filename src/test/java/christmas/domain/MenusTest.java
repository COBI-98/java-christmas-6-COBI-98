package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class MenusTest {

    @DisplayName("validateDuplicateFromMenus() : 중복메뉴를 입력한 경우")
    @ParameterizedTest
    @CsvSource({"양송이수프,양송이수프,2","시저샐러드,시저샐러드,1"})
    void validateDuplicateFromMenus_fail(String menu1, String menu2, String quantity) throws Exception{
        //given
        String exceptionMessage = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
        List<Menu> menus = List.of(new Menu(menu1, quantity), new Menu(menu2, quantity));

        //when //then
        assertThatThrownBy(() -> new Menus(menus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(exceptionMessage);
    }

    @DisplayName("validateMaxQuantityFromMenus() : 최대 주문을 넘어간 경우")
    @ParameterizedTest
    @CsvSource({"타파스,바비큐립,제로콜라,10","양송이수프,시저샐러드,티본스테이크,7"})
    void validateMaxQuantityFromMenus_fail(String menu1, String menu2, String menu3
            , String quantity) throws Exception{
        //given
        String exceptionMessage = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
        List<Menu> menus = List.of(new Menu(menu1, quantity),
                new Menu(menu2, quantity),
                new Menu(menu3, quantity));

        //when //then
        assertThatThrownBy(() -> new Menus(menus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(exceptionMessage);
    }
}