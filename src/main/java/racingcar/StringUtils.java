package racingcar;

import java.util.Arrays;
import java.util.List;

public class StringUtils {

    public static List<String> extract(final String text, final String delimiter) {
        validate(text.trim(), delimiter);

        return Arrays.stream(text.split(delimiter))
                .map(String::trim)
                .toList();
    }

    private static void validate(final String text, final String delimiter) {
        if (text.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 입력이 비어 있습니다.");
        }
        if (text.contains(delimiter + delimiter)) {
            throw new IllegalArgumentException("[ERROR] 연속된 구분자는 허용되지 않습니다.");
        }
        if (text.startsWith(delimiter)) {
            throw new IllegalArgumentException("[ERROR] 시작이 구분자입니다.");
        }
        if (text.endsWith(delimiter)) {
            throw new IllegalArgumentException("[ERROR] 끝이 구분자입니다.");
        }
    }

}
