package racingcar;

import java.util.List;
import racingcar.model.CarName;
import racingcar.model.Game;
import racingcar.model.RandomNumberGenerator;
import racingcar.model.Referee;
import racingcar.model.RoundCount;
import racingcar.model.WoowaCourseRandomNumberGenerator;
import racingcar.view.ConsoleInputReader;
import racingcar.view.GameInputView;
import racingcar.view.GameOutputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        GameInputView gameInputView = new GameInputView(new ConsoleInputReader());
        GameOutputView gameOutputView = new GameOutputView();
        List<CarName> carNames = gameInputView.readCarNames();
        RoundCount roundCount = gameInputView.readRoundCount();
        RandomNumberGenerator woowaCourseRandomNumberGenerator = new WoowaCourseRandomNumberGenerator();
        Referee referee = new Referee();
        Game game = Game.init(carNames, roundCount.roundCount(), referee);
        game.play(woowaCourseRandomNumberGenerator, gameOutputView);

    }
}
