package christmas.domain.event;

import christmas.domain.Date;
import christmas.domain.MenuType;
import christmas.domain.Order;

public final class FreeGiftEvent implements Event {

    private static final int CHRISTMAS_EVENT_MONTH = 12;
    private static final int CHRISTMAS_EVENT_MIN_DAY = 1;
    private static final int CHRISTMAS_EVENT_MAX_DAY = 31;
    private static final int CHRISTMAS_EVENT_FREE_STANDARD = 120_000;
    private String benefitGift;
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
        if (beforeMoney >= CHRISTMAS_EVENT_FREE_STANDARD){
            MenuType champagne = MenuType.CHAMPAGNE;
            benefitGift = champagne.getTitle();
            benefit = champagne.getPrice();
        }
        return benefit;
    }
}
