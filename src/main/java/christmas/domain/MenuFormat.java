package christmas.domain;

import static christmas.message.ErrorMessages.INVALID_DATE_RANGE;
import static christmas.message.ErrorMessages.INVALID_ORDER_FORMAT;

import java.util.List;

public record MenuFormat(List<String> menuFormat) {

    private static final String SPLIT_DELIMITER = "-";
    private static final String DUPLICATION_DELIMITER_FORMAT = String.format(".*--.*");

    public MenuFormat {
        validateContainsDelimiterFromMenuFormat(menuFormat);
        validateEmptyFromMenuFormat(menuFormat);
        validateDuplicationDelimiterFromMenuFormant(menuFormat);
    }

    private void validateContainsDelimiterFromMenuFormat(List<String> menuFormat) {
        boolean hasDelimiter = menuFormat.stream().allMatch(
                menu -> menu.contains(SPLIT_DELIMITER)
        );

        if (!hasDelimiter){
            throw new IllegalArgumentException(INVALID_ORDER_FORMAT.getMessage());
        }
    }

    private void validateEmptyFromMenuFormat(List<String> menuFormat) {
        boolean hasString = !menuFormat.isEmpty() && menuFormat.stream().allMatch(
                menu -> !menu.isEmpty()
        );

        if (!hasString){
            throw new IllegalArgumentException(INVALID_ORDER_FORMAT.getMessage());
        }
    }

    private void validateDuplicationDelimiterFromMenuFormant(List<String> menuFormat) {
        boolean hasConsecutiveDelimiters = menuFormat.stream().anyMatch(
                menu -> menu.matches(DUPLICATION_DELIMITER_FORMAT)
        );
        if (hasConsecutiveDelimiters) {
            throw new IllegalArgumentException(INVALID_ORDER_FORMAT.getMessage());
        }
    }
}
