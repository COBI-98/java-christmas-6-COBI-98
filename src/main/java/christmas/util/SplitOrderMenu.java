package christmas.util;

import christmas.domain.menu.MenuFormat;
import java.util.Arrays;
import java.util.stream.Collectors;

public class SplitOrderMenu {
    private static final String SPLIT_DELIMITER = ",";

    public static MenuFormat splitOrderMenu(String input) {

        return new MenuFormat(Arrays.stream(input.split(SPLIT_DELIMITER))
                .collect(Collectors.toList()));
    }
}
