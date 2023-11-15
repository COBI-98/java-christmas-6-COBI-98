package christmas.domain;

import java.text.DecimalFormat;
import java.util.Arrays;

public enum MenuType {
    BUTTON_MUSHROOM_SOUP("양송이수프", 6_000),
    TAPAS("타파스", 5_500),
    CAESAR_SALAD("시저샐러드", 8_000),
    T_BONE_STEAK("티본스테이크", 55_000),
    BARBECUE_RIBS("바비큐립", 54_000),
    SEAFOOD_PASTA("해산물파스타", 35_000),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000),
    CHOCOLATE_CAKE("초코케이크", 15_000),
    ICE_CREAM("아이스크림", 5_000),
    ZERO_COLA("제로콜라", 3_000),
    RED_WINE("레드와인", 60_000),
    CHAMPAGNE("샴페인", 25_000),
    EMPTY("없음", 0);

    private final String title;
    private final int price;

    MenuType(String title, int price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public static MenuType findByMenuName(String title){
        return Arrays.stream(MenuType.values())
                .filter(menuType -> menuType.hasMenuName(title))
                .findAny()
                .orElse(null);
    }

    private boolean hasMenuName(String menuName) {
        return title.equals(menuName);
    }

    @Override
    public String toString() {
        return String.format("%s(%s)", title, formatPrice(price));
    }

    private String formatPrice(int price) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(price);
    }
}
