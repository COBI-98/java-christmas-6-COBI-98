package christmas.domain.event;

import christmas.domain.Date;
import christmas.domain.Order;

public final class SpecialDiscountEvent implements Event {

    private static final int CHRISTMAS_EVENT_MONTH = 12;
    private static final int CHRISTMAS_EVENT_MIN_DAY = 1;
    private static final String CHRISTMAS_EVENT_DAY = "12월 25일";
    private static final int CHRISTMAS_EVENT_MAX_DAY = 31;
    private static final int HOLIDAY_DISCOUNT_MONEY = 1_000;
    private int benefit;

    @Override
    public boolean isApplicable(Date date) {
        return isEventPeriod(date) && isEventCondition(date);
    }

    private static boolean isEventPeriod(Date date) {
        return date.getMonth() == CHRISTMAS_EVENT_MONTH &&
                date.getDay() >= CHRISTMAS_EVENT_MIN_DAY
                && date.getDay() <= CHRISTMAS_EVENT_MAX_DAY;
    }

    private static boolean isEventCondition(Date date) {
        return date.isSunday() || date.toString().equals(CHRISTMAS_EVENT_DAY);
    }

    @Override
    public int calculateDiscount(Date date, Order order) {
        benefit = HOLIDAY_DISCOUNT_MONEY;
        return benefit;
    }

}
