package racingcar.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.model.CarName;
import racingcar.model.RoundCount;

class GameInputViewTest {
    static Stream<Arguments> carNameCases() {
        return Stream.of(
                Arguments.of("pobi,woni,jun", List.of("pobi", "woni", "jun")),
                Arguments.of("  pobi ,  woni ,  jun  ", List.of("pobi", "woni", "jun"))
        );
    }

    static Stream<Arguments> roundCountCases() {
        return Stream.of(
                Arguments.of("10", 10),
                Arguments.of(" 10", 10)
        );
    }

    @DisplayName("유효한 입력을 CarName 리스트로 변환")
    @ParameterizedTest
    @MethodSource("carNameCases")
    void read_carNames(String input, List<String> expected) {
        // given
        InputReader mockReader = () -> input;
        GameInputView inputView = new GameInputView(mockReader);

        // when
        List<CarName> names = inputView.readCarNames();

        // then
        assertThat(names)
                .extracting(CarName::name)
                .containsExactlyElementsOf(expected);
    }

    @DisplayName("예외: 빈 문자열 입력")
    @ParameterizedTest
    @NullAndEmptySource
    void validate_null_and_empty(String input) {
        // given
        InputReader mockReader = () -> input;
        GameInputView view = new GameInputView(mockReader);

        // expect
        assertThatThrownBy(() -> view.readCarNames())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("예외: 연속된 구분자 입력")
    @ParameterizedTest
    @ValueSource(strings = {"pobi,,woni"})
    void validate_continuous_delimiter(String input) {
        // given
        InputReader mockReader = () -> input;
        GameInputView view = new GameInputView(mockReader);

        // expect
        assertThatThrownBy(() -> view.readCarNames())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("예외: 구분자로 시작하거나 끝나는 입력")
    @ParameterizedTest
    @ValueSource(strings = {",pobi,woni", "pobi,woni,"})
    void validate_boundary_delimiter(String input) {
        // given
        InputReader mockReader = () -> input;
        GameInputView view = new GameInputView(mockReader);

        // expect
        assertThatThrownBy(() -> view.readCarNames())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("유효한 입력을 RoundCount로 변환")
    @ParameterizedTest
    @MethodSource("roundCountCases")
    void read_round_count(String input, int expected) {
        // given
        InputReader mockReader = () -> input;
        GameInputView inputView = new GameInputView(mockReader);

        // when
        RoundCount roundCount = inputView.readRoundCount();

        // then
        assertThat(roundCount)
                .extracting(RoundCount::roundCount)
                .isEqualTo(expected);
    }

    @DisplayName("예외: 시도 횟수 빈 값 입력 ")
    @ParameterizedTest
    @NullAndEmptySource
    void validate_round_count_input(String input) {
        // given
        InputReader mockReader = () -> input;
        GameInputView inputView = new GameInputView(mockReader);

        // expect
        assertThatThrownBy(() -> inputView.readRoundCount())
                .isInstanceOf(IllegalArgumentException.class);
    }
}
