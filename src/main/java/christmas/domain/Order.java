package christmas.domain;

import java.util.List;

public class Order {

    private Menus menus;
    private int beforeMoney;

    public Order(Menus menus) {
        this.menus = menus;
        this.beforeMoney = createTotalOrderMoney(menus);
    }

    private static int createTotalOrderMoney(Menus menus) {
        return menus.menuList().stream()
                .mapToInt(menu -> {
                    MenuType menuType = MenuType.findByMenuName(menu.getMenuName());
                    int menuQuantity = menu.getMenuQuantity();
                    return menuType.getPrice() * menuQuantity;
                })
                .sum();
    }

    public Menus getMenus() {
        return menus;
    }

    public int getBeforeMoney() {
        return beforeMoney;
    }
}
