package racingcar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import racingcar.view.GameInputView;
import racingcar.view.InputReader;

class GameInputViewTest {
    static Stream<Arguments> carNameCases() {
        return Stream.of(
                Arguments.of("pobi,woni,jun", List.of("pobi", "woni", "jun")),
                Arguments.of("  pobi ,  woni ,  jun  ", List.of("pobi", "woni", "jun"))
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

        // when & then
        assertThatThrownBy(() -> view.readCarNames())
                .isInstanceOf(IllegalArgumentException.class);
    }
}
