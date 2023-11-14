package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuGroupTest {

    @DisplayName("toString() : 메뉴판 타입 요구사항 재정의")
    @Test
    void menu_group_to_string_success() throws Exception {
        //given
        MenuGroup appetizer = MenuGroup.APPETIZER;

        //when
        String expectedMessage = """
                <에피타이저>
                양송이수프(6,000), 타파스(5,500), 시저샐러드(8,000)""";

        // then
        assertThat(appetizer.toString()).isEqualTo(expectedMessage);
    }
}