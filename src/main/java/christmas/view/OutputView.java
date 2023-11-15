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
    private static final String RESTAURANT_BENEFIT_TITLE = "<혜택 내역>";
    private static final String RESTAURANT_TOTAL_BENEFIT_TITLE = "<총혜택 금액>";
    private static final String RESTAURANT_FINAL_PAYMENT_AMOUNT = "<할인 후 예상 결제 금액>";
    private static final String RESTAURANT_EVENT_BADGE_TITLE = "<12월 이벤트 배지>";
    private static final String AMOUNT_NOTATION = "#,###";
    private static final String AMOUNT_FORMAT = "%s원";
    private static final String EVENTS_EMPTY = "없음";

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

    public static void printBasicPreview(Date date, Menus menus, Order order) {
        printRestaurantEventPreview(date);
        printRestaurantOrderMenu(menus);
        printTotalAmountBeforeDiscount(order);
    }

    private static void printRestaurantEventPreview(Date date) {
        System.out.println(String.format(RESTAURANT_EVENT_PREVIEW, date.toString()));
        System.out.println();
    }

    private static void printRestaurantOrderMenu(Menus menus) {
        System.out.println(RESTAURANT_ORDER_MENU_TITLE);
        menus.menuList().stream()
                .map(Menu::toString)
                .forEach(System.out::println);
        System.out.println();
    }

    private static void printTotalAmountBeforeDiscount(Order order) {
        System.out.println(RESTAURANT_BEFORE_DISCOUNT_TOTAL_PRICE);
        int totalPrice = order.getBeforeMoney();
        System.out.println(String.format(AMOUNT_FORMAT, formatPrice(totalPrice)));
        System.out.println();
    }


    public static void printPlanner(Order order, Planner planner) {
        printFreeGift(planner);
        printBenefitList(planner);
        printTotalBenefitAmount(planner);
        printFinalPaymentAmount(order, planner);
        printRestaurantEventBadge(planner);
    }

    private static void printFreeGift(Planner planner) {
        System.out.println(RESTAURANT_FREE_GIFT_TITLE);
        if (isEventEmpty(planner)) {
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

    private static void printBenefitList(Planner planner){
        System.out.println(RESTAURANT_BENEFIT_TITLE);
        if (isEventEmpty(planner)) {
            return;
        }
        planner.getEvents().stream()
                .filter(event -> event.getBenefit() != 0)
                .map(event -> event.getEventName() +
                        ": -" +
                        String.format(AMOUNT_FORMAT, formatPrice(event.getBenefit())))
                .forEach(System.out::println);
        System.out.println();
    }

    private static void printTotalBenefitAmount(Planner planner){
        System.out.println(RESTAURANT_TOTAL_BENEFIT_TITLE);
        if (planner.getAfterAmount() == 0){
            System.out.println(String.format(AMOUNT_FORMAT,planner.getAfterAmount()));
            System.out.println();
            return;
        }
        System.out.println("-" + String.format(AMOUNT_FORMAT,formatPrice(planner.getAfterAmount())));
        System.out.println();
    }

    private static void printFinalPaymentAmount(Order order,Planner planner){
        System.out.println(RESTAURANT_FINAL_PAYMENT_AMOUNT);
        int finalPaymentAmount = order.getBeforeMoney() - planner.getAfterAmount();
        finalPaymentAmount = calculateFreeGift(planner, finalPaymentAmount);
        System.out.println(String.format(AMOUNT_FORMAT,formatPrice(finalPaymentAmount)));
        System.out.println();
    }

    private static int calculateFreeGift(Planner planner, int finalPaymentAmount) {
        Menu giveawayMenu = planner.getEvents().stream()
                .filter(event -> event instanceof FreeGiftEvent)
                .map(event -> ((FreeGiftEvent) event).getBenefitGift())
                .findFirst().orElse(null);
        if (giveawayMenu != null){
            MenuType menu = MenuType.findByMenuName(giveawayMenu.getMenuName());
            finalPaymentAmount += menu.getPrice();
        }
        return finalPaymentAmount;
    }

    private static void printRestaurantEventBadge(Planner planner) {
        System.out.println(RESTAURANT_EVENT_BADGE_TITLE);
        System.out.println(planner.getEventBadge().getName());
    }

    private static boolean isEventEmpty(Planner planner) {
        if (validateEventsEmpty(planner)){
            System.out.println(EVENTS_EMPTY);
            System.out.println();
            return true;
        }
        return false;
    }

    private static boolean validateEventsEmpty(Planner planner) {
        return planner.getEvents().isEmpty();
    }

    private static String formatPrice(int totalPrice) {
        DecimalFormat decimalFormat = new DecimalFormat(AMOUNT_NOTATION);
        return decimalFormat.format(totalPrice);
    }

    public static void printException(Exception exception) {
        System.out.println(exception.getMessage());
    }
}
