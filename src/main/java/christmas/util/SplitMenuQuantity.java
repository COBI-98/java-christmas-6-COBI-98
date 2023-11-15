package christmas.util;

import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuFormat;
import christmas.domain.menu.Menus;
import java.util.stream.Collectors;

public class SplitMenuQuantity {

    private static final String SPLIT_DELIMITER = "-";

    public static Menus createMenus(MenuFormat menuFormat) {

        return new Menus(menuFormat.menuFormat().stream()
                .map(menu -> menu.split(SPLIT_DELIMITER))
                .map(parts -> new Menu(parts[0], parts[1]))
                .collect(Collectors.toList()));
    }
}
