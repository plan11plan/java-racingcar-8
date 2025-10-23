package racingcar.view;

public record RoundCount(int roundCount) {
    private static final int MIN_ROUNDS = 1;
    private static final int MAX_ROUNDS = 1_000_000;

    public RoundCount {
        validateRound(roundCount);
    }

    private void validateRound(int round) {
        if (round < MIN_ROUNDS) {
            throw new IllegalArgumentException("[ERROR] 범위 미만");
        }
        if (round > MAX_ROUNDS) {
            throw new IllegalArgumentException("[ERROR] 범위 초과");
        }
    }
}
