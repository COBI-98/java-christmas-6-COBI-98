package christmas.domain;

import christmas.domain.event.ChristmasDailyDiscountEvent;
import christmas.domain.event.Event;
import christmas.domain.event.FreeGiftEvent;
import christmas.domain.event.SpecialDiscountEvent;
import christmas.domain.event.WeekdayDiscountEvent;
import christmas.domain.event.WeekendDiscountEvent;
import java.util.Collections;
import java.util.List;

public class Planner {

    private static final int CHRISTMAS_EVENT_MIN_ORDER_AMOUNT = 10_000;
    private static final int START_BENEFIT_AMOUNT = 0;
    private final List<Event> events;
    private int afterAmount;
    private EventBadge eventBadge;

    public Planner(Date date, Order order) {
        if (order.getBeforeMoney() < CHRISTMAS_EVENT_MIN_ORDER_AMOUNT){
            this.events = Collections.EMPTY_LIST;
            this.afterAmount = START_BENEFIT_AMOUNT;
            return;
        }

        this.events = List.of(
                new ChristmasDailyDiscountEvent(),
                new WeekdayDiscountEvent(),
                new WeekendDiscountEvent(),
                new FreeGiftEvent(),
                new SpecialDiscountEvent()
        );
        this.afterAmount = calculateBenefits(date, order);
    }

    public int calculateBenefits(Date date, Order order) {

        return events.stream()
                .filter(event -> event.isApplicable(date))
                .mapToInt(event -> event.calculateDiscount(date, order))
                .sum();
    }

    public List<Event> getEvents() {
        return events;
    }

    public int getAfterAmount() {
        return afterAmount;
    }

    public EventBadge getEventBadge() {
        return EventBadge.findByBadgeType(afterAmount);
    }
}
