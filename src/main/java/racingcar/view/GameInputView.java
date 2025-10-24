package racingcar.view;

import java.util.List;
import racingcar.StringUtils;
import racingcar.model.CarName;
import racingcar.model.RoundCount;

public class GameInputView {
    private static final String DELIMITER = ",";
    private final InputReader inputReader;

    public GameInputView(InputReader inputReader) {
        this.inputReader = inputReader;
    }

    public List<CarName> readCarNames() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");

        String input = inputReader.readLine();
        validateCarNameInput(input);
        List<String> carNames = StringUtils.extract(input, DELIMITER);

        return carNames.stream()
                .map(CarName::new)
                .toList();
    }

    public RoundCount readRoundCount() {
        System.out.println("시도할 횟수는 몇 회인가요?");

        String input = inputReader.readLine();
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 입력이 비어 있습니다.");
        }
        input = input.trim();
        int roundCount = 0;
        try {
            roundCount = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자 형식이 아닙니다.");
        }
        return new RoundCount(roundCount);
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
