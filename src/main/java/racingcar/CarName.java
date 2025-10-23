package racingcar;

public record CarName(String name) {

    public CarName {
        validateBlank(name);
    }

    private void validateBlank(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 이름이 비어있습니다.");
        }
    }

}
