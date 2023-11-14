package christmas.domain;

public class Menu {

    private MenuName menuName;
    private MenuQuantity menuQuantity;

    public Menu(final String name, final String quantity) {
        this.menuName = new MenuName(name);
        this.menuQuantity = new MenuQuantity(quantity);
    }

    public String getMenuName() {
        return menuName.name();
    }

    public int getMenuQuantity() {
        return stringQuantityToInt(menuQuantity.quantity());
    }

    private int stringQuantityToInt(String quantity) {
        return Integer.parseInt(quantity);
    }
}
