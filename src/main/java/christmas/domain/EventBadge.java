package christmas.domain;

import java.util.Arrays;

public enum EventBadge {
    SANTA("산타", 20_000),
    TREE("트리", 10_000),
    START("별", 5_000),
    EMPTY("없음",0);

    private String name;
    private int amount;

    EventBadge(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public static EventBadge findByBadgeType(int benefitAmount){
        return Arrays.stream(EventBadge.values())
                .filter(eventBadge -> eventBadge.hasEventBadge(benefitAmount))
                .findFirst()
                .orElse(EMPTY);
    }

    private boolean hasEventBadge(int benefitAmount) {
        return benefitAmount > amount;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }
}
