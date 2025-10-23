package racingcar.view;

import java.util.List;
import racingcar.CarName;
import racingcar.StringUtils;

public class GameInputView {
    private static final String DELIMITER = ",";
    private final InputReader inputReader;

    public GameInputView(InputReader inputReader) {
        this.inputReader = inputReader;
    }

    public List<CarName> readCarNames() {
        String input = inputReader.readLine();
        validateCarNameInput(input);
        List<String> carNames = StringUtils.extract(input, DELIMITER);

        return carNames.stream()
                .map(CarName::new)
                .toList();
    }

    private void validateCarNameInput(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 입력이 비어 있습니다.");
        }
        if (input.contains(DELIMITER + DELIMITER)) {
            throw new IllegalArgumentException("[ERROR] 연속된 구분자는 허용되지 않습니다.");
        }
        if (input.startsWith(DELIMITER) || input.endsWith(DELIMITER)) {
            throw new IllegalArgumentException("[ERROR] 구분자로 시작하거나 끝날 수 없습니다.");
        }
    }
}
