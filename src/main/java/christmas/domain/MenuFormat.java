package christmas.domain;

import static christmas.message.ErrorMessages.INVALID_ORDER_FORMAT;

import java.util.List;

public record MenuFormat(List<String> menuFormat) {

    private static final String SPLIT_DELIMITER = "-";

    public MenuFormat {
        validateContainsDelimiterFromMenuFormat(menuFormat);
        validateEmptyFromMenuFormat(menuFormat);
    }

    private void validateContainsDelimiterFromMenuFormat(List<String> menuFormat) {
        boolean validatedResult = menuFormat.stream().allMatch(
                menu -> menu.contains(SPLIT_DELIMITER)
        );

        if (!validatedResult){
            throw new IllegalArgumentException(INVALID_ORDER_FORMAT.getMessage());
        }
    }

    private void validateEmptyFromMenuFormat(List<String> menuFormat) {
        boolean validatedResult = !menuFormat.isEmpty() && menuFormat.stream().allMatch(
                menu -> !menu.isEmpty()
        );

        if (!validatedResult){
            throw new IllegalArgumentException(INVALID_ORDER_FORMAT.getMessage());
        }
    }
}
