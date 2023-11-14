package christmas.util;

import christmas.domain.Menu;
import christmas.domain.MenuFormat;
import christmas.domain.MenuName;
import christmas.domain.MenuQuantity;
import christmas.domain.Menus;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SplitMenuQuantity {

    private static final String SPLIT_DELIMITER = "-";

    public static Menus splitMenuQuantity(MenuFormat menuFormat) {

        return new Menus(menuFormat.menuFormat().stream()
                .map(menu -> menu.split(SPLIT_DELIMITER))
                .map(parts -> new Menu(parts[0], parts[1]))
                .collect(Collectors.toList()));
    }
}
