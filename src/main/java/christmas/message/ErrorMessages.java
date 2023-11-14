package christmas.message;

public enum ErrorMessages {
    INVALID_DATE_RANGE("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_NUMBER_TYPE("[ERROR] 숫자만 입력해 주세요!"),
    INVALID_ORDER_FORMAT("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");

    private String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
