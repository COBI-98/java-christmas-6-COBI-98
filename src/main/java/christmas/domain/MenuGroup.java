package christmas.domain;

import java.util.Arrays;
import java.util.List;

public enum MenuGroup {
    APPETIZER("에피타이저", Arrays.asList(MenuType.BUTTON_MUSHROOM_SOUP, MenuType.TAPAS, MenuType.CAESAR_SALAD)),
    MAIN("메인", Arrays.asList(MenuType.T_BONE_STEAK, MenuType.BARBECUE_RIBS, MenuType.SEAFOOD_PASTA, MenuType.CHRISTMAS_PASTA)),
    DESSERT("디저트", Arrays.asList(MenuType.CHOCOLATE_CAKE, MenuType.ICE_CREAM)),
    BEVERAGE("음료", Arrays.asList(MenuType.ZERO_COLA, MenuType.RED_WINE, MenuType.CHAMPAGNE));

    private final String title;
    private final List<MenuType> menuList;

    MenuGroup(String title, List<MenuType> menuList) {
        this.title = title;
        this.menuList = menuList;
    }

    public String getTitle() {
        return title;
    }

    public List<MenuType> getMenuList() {
        return menuList;
    }
}
