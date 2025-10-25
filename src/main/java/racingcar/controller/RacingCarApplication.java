package racingcar.controller;

import java.util.List;
import racingcar.model.CarName;
import racingcar.model.Game;
import racingcar.model.GameResult;
import racingcar.model.RandomNumberGenerator;
import racingcar.model.Referee;
import racingcar.model.RoundCount;
import racingcar.view.GameInputView;
import racingcar.view.GameOutputView;

public class RacingCarApplication {
    private final GameInputView inputView;
    private final GameOutputView outputView;
    private final RandomNumberGenerator randomNumberGenerator;
    private final Referee referee;

    public RacingCarApplication(GameInputView inputView, GameOutputView outputView,
                                RandomNumberGenerator randomNumberGenerator, Referee referee) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.randomNumberGenerator = randomNumberGenerator;
        this.referee = referee;
    }

    public void run() {
        List<CarName> carNames = inputView.readCarNames();
        RoundCount roundCount = inputView.readRoundCount();

        Game game = Game.init(carNames, roundCount.roundCount(), referee);
        GameResult result = game.play(randomNumberGenerator);

        outputView.printGameResult(result);
    }
}
