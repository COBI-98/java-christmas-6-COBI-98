package christmas.domain;

import static christmas.message.ErrorMessages.INVALID_ORDER_FORMAT;

import christmas.message.ErrorMessages;
import java.util.List;
import java.util.stream.Collectors;

public record Menus(List<Menu> menuList) {


    public Menus {
        validateDuplicateFromMenus(menuList);
    }

    private void validateDuplicateFromMenus(List<Menu> menuList) {
        long distinctCount = menuList.stream()
                .map(menu -> menu.getMenuName())
                .distinct()
                .count();

        if (menuList.size() != distinctCount){
            throw new IllegalArgumentException(INVALID_ORDER_FORMAT.getMessage());
        }
    }
}
