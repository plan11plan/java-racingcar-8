package racingcar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StringUtilsTest {
    private static final String DELIMITER = ",";


    @Test
    @DisplayName("구분자로 문자열을 분리한다")
    void split_success() {
        // given
        String text = "pobi,woni";

        // when
        List<String> result = StringUtils.extract(text, DELIMITER);

        // then
        assertThat(result).containsExactly("pobi", "woni");
    }

    @Test
    @DisplayName("예외: 연속된 구분자/빈 이름")
    void split_fail_consecutiveDelimiter() {
        // given
        String text = "pobi,,woni";

        // expect
        assertThatThrownBy(() -> StringUtils.extract(text, DELIMITER))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("예외: 시작이 구분자")
    void split_fail_startsWithDelimiter() {
        // given
        String text = ",pobi";

        // expect
        assertThatThrownBy(() -> StringUtils.extract(text, DELIMITER))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("예외: 끝이 구분자")
    void split_fail_endsWithDelimiter() {
        // given
        String text = "pobi,";

        // expect
        assertThatThrownBy(() -> StringUtils.extract(text, DELIMITER))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
