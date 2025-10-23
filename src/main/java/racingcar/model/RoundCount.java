package racingcar.model;

public record RoundCount(int roundCount) {
    private static final int MIN_ROUNDS = 1;
    private static final int MAX_ROUNDS = 1_000_000;

    public RoundCount {
        validateRound(roundCount);
    }

    private void validateRound(int round) {
        if (round < MIN_ROUNDS) {
            throw new IllegalArgumentException("[ERROR] 시도 횟수는 %d 이상이어야 합니다.".formatted(MIN_ROUNDS));
        }
        if (round > MAX_ROUNDS) {
            throw new IllegalArgumentException("[[ERROR] 시도 횟수는 %d 이하이어야 합니다.".formatted(MAX_ROUNDS));
        }
    }
}
