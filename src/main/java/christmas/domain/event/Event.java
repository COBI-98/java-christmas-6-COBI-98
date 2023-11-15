package christmas.domain.event;

import christmas.domain.Date;
import christmas.domain.Order;

public sealed interface Event permits ChristmasDailyDiscountEvent {

    boolean isApplicable(Date date);

    int calculateDiscount(Date date, Order order);
}