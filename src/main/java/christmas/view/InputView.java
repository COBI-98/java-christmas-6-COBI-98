package christmas.view;

import static christmas.message.ErrorMessages.INVALID_NUMBER_TYPE;

import camp.nextstep.edu.missionutils.Console;
import christmas.message.ErrorMessages;

public class InputView {
    private static final String INPUT_VISIT_DATE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";

    public static int inputDate(){
        System.out.println(INPUT_VISIT_DATE);
        return convertToInt(Console.readLine());
    }

    private static int convertToInt(String input) {
        try {
            return Integer.parseInt(input);
        }catch (NumberFormatException e){
            throw new IllegalArgumentException(INVALID_NUMBER_TYPE.getMessage());
        }
    }
}
