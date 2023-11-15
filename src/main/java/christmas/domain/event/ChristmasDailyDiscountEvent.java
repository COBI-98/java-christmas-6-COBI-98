package christmas.domain.event;

import christmas.domain.Date;
import christmas.domain.Order;
import java.time.LocalDate;
import java.time.Period;

public final class ChristmasDailyDiscountEvent implements Event {

    private static final int CHRISTMAS_EVENT_YEAR = 2023;
    private static final int CHRISTMAS_EVENT_MONTH = 12;
    private static final int CHRISTMAS_EVENT_MIN_DAY = 1;
    private static final int CHRISTMAS_EVENT_MAX_DAY = 25;
    private static final int CHRISTMAS_EVENT_START_MONEY = 1_000;
    private static final int CHRISTMAS_EVENT_DISCOUNT_UNIT = 100;
    private int benefit;

    public ChristmasDailyDiscountEvent() {
        this.benefit = benefit;
    }

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
        LocalDate startDay = LocalDate.of(CHRISTMAS_EVENT_YEAR, CHRISTMAS_EVENT_MONTH, CHRISTMAS_EVENT_MIN_DAY);
        LocalDate visitDay = date.createDateTimeFormat();
        Period period = Period.between(startDay, visitDay);
        return CHRISTMAS_EVENT_START_MONEY + CHRISTMAS_EVENT_DISCOUNT_UNIT * period.getDays();
    }
}
