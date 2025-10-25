package racingcar;

import camp.nextstep.edu.missionutils.Console;
import racingcar.controller.RacingCarApplication;
import racingcar.model.Referee;
import racingcar.model.WoowaCourseRandomNumberGenerator;
import racingcar.view.ConsoleInputReader;
import racingcar.view.GameInputView;
import racingcar.view.GameOutputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        try {
            RacingCarApplication application = new RacingCarApplication(
                    new GameInputView(new ConsoleInputReader()),
                    new GameOutputView(),
                    new WoowaCourseRandomNumberGenerator(),
                    new Referee()
            );

            application.run();
        } finally {
            Console.close();
        }
    }
}
