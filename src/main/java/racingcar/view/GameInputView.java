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
        List<String> carNames = StringUtils.extract(input, DELIMITER);

        return carNames.stream()
                .map(CarName::new)
                .toList();
    }

}
