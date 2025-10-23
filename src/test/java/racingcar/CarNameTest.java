package racingcar;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class CarNameTest {

    @DisplayName("예외: 빈 이름 입력")
    @ParameterizedTest
    @NullAndEmptySource
    public void validate_null_and_empty(String input) {
        assertThatThrownBy(() -> new CarName(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("예외: 최대 글자 초과")
    @ParameterizedTest
    @ValueSource(strings = {"123456"})
    public void validate_maxLength(String input) {
        assertThatThrownBy(() -> new CarName(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
