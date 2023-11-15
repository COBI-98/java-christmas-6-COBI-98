package christmas.domain.event;

import christmas.domain.calendar.Date;
import christmas.domain.calendar.Order;
import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuType;

public final class FreeGiftEvent implements Event {

    private static final int CHRISTMAS_EVENT_MONTH = 12;
    private static final int CHRISTMAS_EVENT_MIN_DAY = 1;
    private static final int CHRISTMAS_EVENT_MAX_DAY = 31;
    private static final int CHRISTMAS_EVENT_FREE_STANDARD = 120_000;
    private static final String CHRISTMAS_EVENT_FREE_QUANTITY = "1";
    private static final String FREE_GIFT_EVENT_TITLE = "증정 이벤트";
    private Menu benefitGift = new Menu(MenuType.EMPTY.getTitle(), CHRISTMAS_EVENT_FREE_QUANTITY);
    private int benefit;

    @Override
    public boolean isApplicable(Date date) {
        return isEventPeriod(date);
    }

    private static boolean isEventPeriod(Date date) {
        return date.getMonth() == CHRISTMAS_EVENT_MONTH &&
                date.getDay() >= CHRISTMAS_EVENT_MIN_DAY
                && date.getDay() <= CHRISTMAS_EVENT_MAX_DAY;
    }

    @Override
    public int calculateDiscount(Date date, Order order) {
        int beforeMoney = order.getBeforeMoney();
        if (beforeMoney >= CHRISTMAS_EVENT_FREE_STANDARD) {
            MenuType champagne = MenuType.CHAMPAGNE;
            benefitGift = new Menu(champagne.getTitle(), CHRISTMAS_EVENT_FREE_QUANTITY);
            benefit = champagne.getPrice();
        }
        return benefit;
    }

    public Menu getBenefitGift() {
        return benefitGift;
    }

    @Override
    public int getBenefit() {
        return benefit;
    }

    @Override
    public String getEventName() {
        return FREE_GIFT_EVENT_TITLE;
    }

}
