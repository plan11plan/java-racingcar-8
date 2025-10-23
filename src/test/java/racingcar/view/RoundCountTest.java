package racingcar.view;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.model.RoundCount;

class RoundCountTest {

    @DisplayName("예외: 범위에서 벗어난 시도 횟수")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 1_000_001})
    public void validate_in_range(int input) {
        Assertions.assertThatThrownBy(() -> new RoundCount(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
