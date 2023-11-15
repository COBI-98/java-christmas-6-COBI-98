package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.menu.MenuFormat;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MenuFormatTest {

    @DisplayName("constructor() : 메뉴 형식 생성")
    @Test
    void menu_formant_constructor() throws Exception {
        //given
        MenuFormat menuFormat = new MenuFormat(List.of("해산물파스타-2", "레드와인-1", "초코케이크-1"));

        //when //then
        assertThat(menuFormat).isNotNull();
    }

    @DisplayName("validateContainsDelimiterFromMenuFormat() : 메뉴 형식이 일치하지 않는 경우")
    @ParameterizedTest
    @CsvSource({"해산물파스타1,레드와인2,초코케이크-1",
            "해산물파스타1,레드와인-2,초코케이크-1,",
            "해산물파스타-12,레드와인,초코케이크-1"
    })
    void validateContainsDelimiterFromMenuFormat_fail(String menu1, String menu2, String menu3) throws Exception {
        //given
        String exceptionMessage = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

        //when //then
        assertThatThrownBy(() -> new MenuFormat(List.of(menu1, menu2, menu3)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(exceptionMessage);
    }

    @DisplayName("validateEmptyFromMenuFormat() : 메뉴 형식이 빈 문자열인 경우")
    @ParameterizedTest
    @CsvSource({
            "'', '', ''",
            "'', '', '초코케이크-1'"
    })
    void validateEmptyFromMenuFormat_fail(String menu1, String menu2, String menu3) throws Exception {
        //given
        String exceptionMessage = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

        //when //then
        assertThatThrownBy(() -> new MenuFormat(List.of(menu1, menu2, menu3)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(exceptionMessage);

    }

    @DisplayName("validateDuplicationDelimiterFromMenuFormant() : 연속된 구분자가 사용되는 경우")
    @ParameterizedTest
    @CsvSource({
            "-----,초코케이크--2,--초코케이크-3"
    })
    void validateDuplicationDelimiterFromMenuFormant_fail(String menu1, String menu2, String menu3) throws Exception {
        //given
        String exceptionMessage = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

        //when //then
        assertThatThrownBy(() -> new MenuFormat(List.of(menu1, menu2, menu3)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(exceptionMessage);

    }

}