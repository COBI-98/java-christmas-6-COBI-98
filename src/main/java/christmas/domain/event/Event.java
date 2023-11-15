package christmas.domain.event;

import christmas.domain.calendar.Date;
import christmas.domain.calendar.Order;

public sealed interface Event permits ChristmasDailyDiscountEvent, WeekdayDiscountEvent, WeekendDiscountEvent, SpecialDiscountEvent,
        FreeGiftEvent {

    boolean isApplicable(Date date);

    int calculateDiscount(Date date, Order order);

    int getBenefit();

    String getEventName();

}
