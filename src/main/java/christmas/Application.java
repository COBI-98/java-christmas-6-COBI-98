package christmas;

import christmas.controller.UtecoRestaurantController;

public class Application {
    public static void main(String[] args) {
        UtecoRestaurantController controller = new UtecoRestaurantController();
        controller.startOperation();
    }
}
