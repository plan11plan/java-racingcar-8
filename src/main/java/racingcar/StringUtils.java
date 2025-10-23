package racingcar;

import java.util.Arrays;
import java.util.List;

public class StringUtils {

    public static List<String> extract(final String text, final String delimiter) {
        if (text == null) {
            throw new IllegalArgumentException("텍스트는 null일 수 없습니다.");
        }
        if (delimiter == null) {
            throw new IllegalArgumentException("구분자는 null일 수 없습니다.");
        }
        return Arrays.stream(text.split(delimiter))
                .map(String::trim)
                .toList();
    }
}
