package christmas.view;

public class OutputView {

    private static final String RESTAURANT_EVENT_INTRO = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";

    public static void printRestaurantIntro(){
        System.out.println(RESTAURANT_EVENT_INTRO);
    }

    public static void printException(Exception exception) {
        System.out.println(exception.getMessage());
    }
}
