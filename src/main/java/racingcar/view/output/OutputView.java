package racingcar.view.output;

import java.util.List;
import racingcar.model.Car;
import racingcar.model.GameResult;

public interface OutputView {
    void printGameResultMessage();

    void printRoundResult(List<Car> cars);

    void printWinnerResult(List<Car> winners);

    void printGameResult(GameResult result);
}
