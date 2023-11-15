package christmas.domain;

import static christmas.message.ErrorMessages.INVALID_ORDER_FORMAT;

import christmas.message.ErrorMessages;

public record MenuQuantity(String quantity) {

    private static final int MIN_QUANTITY = 1;
    private static final int MAX_QUANTITY = 20;

    public MenuQuantity {
        validateEmptyFromMenuQuantity(quantity);
        validateCharacterFromMenuQuantity(quantity);
        validateRangeFromMenuQuantity(quantity);
    }

    private void validateEmptyFromMenuQuantity(String quantity) {
        if (quantity.isEmpty()) {
            throw new IllegalArgumentException(INVALID_ORDER_FORMAT.getMessage());
        }
    }

    private void validateCharacterFromMenuQuantity(String quantity) {
        for (int idx = 0; idx < quantity.length(); idx++) {
            if (!Character.isDigit(quantity.charAt(idx))) {
                throw new IllegalArgumentException(INVALID_ORDER_FORMAT.getMessage());
            }
        }
    }

    private void validateRangeFromMenuQuantity(String quantity){
        int quantityNumber = convertToInt(quantity);
        if (quantityNumber < MIN_QUANTITY || quantityNumber > MAX_QUANTITY){
            throw new IllegalArgumentException(INVALID_ORDER_FORMAT.getMessage());
        }
    }

    private static int convertToInt(String quantity) {
        return Integer.parseInt(quantity);
    }
}
