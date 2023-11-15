package christmas.domain.menu;

import static christmas.message.ErrorMessages.INVALID_ORDER_FORMAT;

public record MenuName(String name) {

    public MenuName {
        validateRestaurantContainsFromMenuName(name);
    }

    private void validateRestaurantContainsFromMenuName(String name) {
        if (MenuType.findByMenuName(name) == null){
            throw new IllegalArgumentException(INVALID_ORDER_FORMAT.getMessage());
        }
    }
}
