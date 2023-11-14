package christmas.domain;

import static christmas.message.ErrorMessages.INVALID_ORDER_FORMAT;

import java.util.List;

public record MenuFormat(List<String> menuFormat) {

    private static final String SPLIT_DELIMITER = "-";

    public MenuFormat {
        validateContainsDelimiterFromMenuFormat(menuFormat);
    }

    private void validateContainsDelimiterFromMenuFormat(List<String> menuFormat) {
        boolean validatedResult = menuFormat.stream().allMatch(
                menu -> menu.contains(SPLIT_DELIMITER)
        );
        if (validatedResult == false){
            throw new IllegalArgumentException(INVALID_ORDER_FORMAT.getMessage());
        }
    }
}
