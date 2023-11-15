package christmas.view;

import christmas.domain.Date;
import christmas.domain.Menu;
import christmas.domain.MenuGroup;
import christmas.domain.MenuType;
import christmas.domain.Menus;
import christmas.domain.Order;
import christmas.domain.Planner;
import christmas.domain.event.FreeGiftEvent;
import java.text.DecimalFormat;
import java.util.List;

public class OutputView {

    private static final String RESTAURANT_EVENT_INTRO = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String RESTAURANT_EVENT_PREVIEW = "%s에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String RESTAURANT_ORDER_MENU_TITLE = "<주문 메뉴>";
    private static final String RESTAURANT_BEFORE_DISCOUNT_TOTAL_PRICE = "<할인 전 총주문 금액>";
    private static final String RESTAURANT_FREE_GIFT_TITLE = "<증정 메뉴>";
    private static final String AMOUNT_NOTATION = "#,###";

    public static void printRestaurantIntro() {
        System.out.println(RESTAURANT_EVENT_INTRO);
    }

    public static void printRestaurantMenu() {
        List<MenuGroup> menuPanList = List.of(MenuGroup.APPETIZER, MenuGroup.MAIN, MenuGroup.DESSERT,
                MenuGroup.BEVERAGE);

        menuPanList.stream()
                .map(menuGroup -> menuGroup.toString() + "\n")
                .forEach(System.out::println);
    }

    public static void printRestaurantEventPreview(Date date) {
        System.out.println(String.format(RESTAURANT_EVENT_PREVIEW, date.toString()));
        System.out.println();
    }

    public static void printRestaurantOrderMenu(Menus menus) {
        System.out.println(RESTAURANT_ORDER_MENU_TITLE);
        menus.menuList().stream()
                .map(Menu::toString)
                .forEach(System.out::println);
        System.out.println();
    }

    public static void printTotalAmountBeforeDiscount(Order order) {
        System.out.println(RESTAURANT_BEFORE_DISCOUNT_TOTAL_PRICE);
        int totalPrice = order.getBeforeMoney();
        System.out.println(String.format(AMOUNT_FORMAT, formatPrice(totalPrice)));
        System.out.println();
    }

    public static void printFreeGift(Planner planner) {
        System.out.println(RESTAURANT_FREE_GIFT_TITLE);
        if (validateEventsEmpty(planner)){
            System.out.println(EVENTS_EMPTY);
            System.out.println();
            return;
        }
        findByGiftMenu(planner);
        System.out.println();
    }

    private static void findByGiftMenu(Planner planner) {
        planner.getEvents().stream()
                .filter(event -> event instanceof FreeGiftEvent)
                .map(event -> ((FreeGiftEvent) event).getBenefitGift())
                .forEach(menu -> {
                    if (menu.getMenuName().equals(EVENTS_EMPTY)){
                        System.out.println(EVENTS_EMPTY);
                        return;
                    }
                    System.out.println(menu.toString());
                });
    }
    private static String formatPrice(int totalPrice) {
        DecimalFormat decimalFormat = new DecimalFormat(AMOUNT_NOTATION);
        return decimalFormat.format(totalPrice);
    }

    public static void printException(Exception exception) {
        System.out.println(exception.getMessage());
    }
}
