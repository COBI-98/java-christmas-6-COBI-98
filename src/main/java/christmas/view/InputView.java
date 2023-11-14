package christmas.view;

import static christmas.message.ErrorMessages.INVALID_NUMBER_TYPE;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.MenuFormat;
import christmas.domain.Menus;
import christmas.util.SplitMenuQuantity;
import christmas.util.SplitOrderMenu;

public class InputView {
    private static final String INPUT_VISIT_DATE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String INPUT_ORDER_MENU = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    public static int inputDate(){
        System.out.println(INPUT_VISIT_DATE);
        return convertToInt(Console.readLine());
    }

    public static Menus inputOrderMenu(){
        System.out.println(INPUT_ORDER_MENU);
        MenuFormat menuFormat = SplitOrderMenu.splitOrderMenu(Console.readLine());

        return SplitMenuQuantity.createMenus(menuFormat);
    }

    private static int convertToInt(String input) {
        try {
            return Integer.parseInt(input);
        }catch (NumberFormatException e){
            throw new IllegalArgumentException(INVALID_NUMBER_TYPE.getMessage());
        }
    }
}
