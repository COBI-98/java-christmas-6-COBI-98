package christmas.domain;

import static christmas.message.ErrorMessages.INVALID_ORDER_FORMAT;

import christmas.message.ErrorMessages;
import java.util.List;
import java.util.stream.Collectors;

public record Menus(List<Menu> menuList) {

    private static final int MAX_QUANTITY = 20;

    public Menus {
        validateDuplicateFromMenus(menuList);
        validateMaxQuantityFromMenus(menuList);
        validateBeverageFromMenus(menuList);
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

    private void validateMaxQuantityFromMenus(List<Menu> menuList) {
        int totalQuantity = menuList.stream()
                .mapToInt(Menu::getMenuQuantity)
                .sum();
        if (totalQuantity > MAX_QUANTITY){
            throw new IllegalArgumentException(INVALID_ORDER_FORMAT.getMessage());
        }
    }

    private void validateBeverageFromMenus(List<Menu> menuList) {

        List<MenuType> menuTypes = findAllByMenuType(menuList);

        List<MenuGroup> menuGroups = findAllByMenuGroup(menuTypes);

        if (menuGroups.size() == 1 && menuGroups.contains(MenuGroup.BEVERAGE)){
            throw new IllegalArgumentException(INVALID_ORDER_FORMAT.getMessage());
        }
    }

    private static List<MenuType> findAllByMenuType(List<Menu> menuList) {
        return menuList.stream()
                .map(menu -> MenuType.findByMenuName(menu.getMenuName()))
                .collect(Collectors.toList());
    }

    private static List<MenuGroup> findAllByMenuGroup(List<MenuType> collect) {
        return collect.stream()
                .map(menuType -> MenuGroup.findByMenuType(menuType))
                .distinct()
                .collect(Collectors.toList());
    }
}
