package christmas.domain.event;

import christmas.domain.calendar.Date;
import christmas.domain.calendar.Order;
import christmas.domain.menu.MenuGroup;
import christmas.domain.menu.MenuType;

public final class WeekendDiscountEvent implements Event {
    private static final int CHRISTMAS_EVENT_MONTH = 12;
    private static final int CHRISTMAS_EVENT_MIN_DAY = 1;
    private static final int CHRISTMAS_EVENT_MAX_DAY = 31;
    private static final int CHRISTMAS_EVENT_DESERT_DISCOUNT = 2_023;
    private static final String WEEKEND_DISCOUNT_EVENT_TITLE = "주말 할인";
    private int benefit;

    @Override
    public boolean isApplicable(Date date) {
        return isEventPeriod(date) &&
                date.isWeekend();
    }

    private static boolean isEventPeriod(Date date) {
        return date.getMonth() == CHRISTMAS_EVENT_MONTH &&
                date.getDay() >= CHRISTMAS_EVENT_MIN_DAY
                && date.getDay() <= CHRISTMAS_EVENT_MAX_DAY;
    }

    @Override
    public int calculateDiscount(Date date, Order order) {
        benefit = order.getMenus().menuList().stream()
                .mapToInt(menu -> {
                    MenuType menuType = MenuType.findByMenuName(menu.getMenuName());
                    MenuGroup menuGroup = MenuGroup.findByMenuType(menuType);
                    int menuQuantity = menu.getMenuQuantity();
                    if (menuGroup == MenuGroup.MAIN) {
                        return CHRISTMAS_EVENT_DESERT_DISCOUNT * menuQuantity;
                    }
                    return 0;
                })
                .sum();
        return benefit;
    }

    @Override
    public int getBenefit() {
        return benefit;
    }

    @Override
    public String getEventName() {
        return WEEKEND_DISCOUNT_EVENT_TITLE;
    }
}
