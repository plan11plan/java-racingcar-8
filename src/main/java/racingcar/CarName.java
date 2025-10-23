package racingcar;

public record CarName(String name) {
    private static final int MAX_NAME_LEN = 5;

    public CarName {
        validateBlank(name);
        validateLength(name, MAX_NAME_LEN);
    }

    private void validateBlank(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 이름이 비어있습니다.");
        }
    }

    private void validateLength(String input, int maxLength) {
        if (input.length() > maxLength) {
            throw new IllegalArgumentException("[ERROR] 이름은 최대 %d글자입니다.".formatted(maxLength));
        }
    }

}
