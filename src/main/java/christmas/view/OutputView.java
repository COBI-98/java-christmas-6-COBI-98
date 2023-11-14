package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.MenuGroup;
import java.util.List;

public class OutputView {

    private static final String RESTAURANT_EVENT_INTRO = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";

    public static void printRestaurantIntro(){
        System.out.println(RESTAURANT_EVENT_INTRO);
    }

    public static void printException(Exception exception) {
        System.out.println(exception.getMessage());
    }

    public static void printRestaurantMenu() {
        List<MenuGroup> menuPanList = List.of(MenuGroup.APPETIZER, MenuGroup.MAIN, MenuGroup.DESSERT, MenuGroup.BEVERAGE);

        menuPanList.stream()
                .map(menuGroup -> menuGroup.toString() + "\n")
                .forEach(System.out::println);
    }
}
