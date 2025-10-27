package racingcar.view.output;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import racingcar.model.Car;
import racingcar.model.GameResult;

public class GameOutputView {
    private final GameResultFormatter formatter;
    private final PrintStream printStream;

    public GameOutputView() {
        this(new GameResultFormatter(), System.out);
    }

    public GameOutputView(OutputStream outputStream) {
        this(new GameResultFormatter(), outputStream);
    }

    public GameOutputView(GameResultFormatter formatter, OutputStream outputStream) {
        this.formatter = formatter;
        this.printStream = new PrintStream(outputStream, true);
    }


    public void printGameResultMessage() {
        printStream.println(formatter.formatGameResultHeader());
    }

    public void printRoundResult(List<Car> cars) {
        printStream.print(formatter.formatRoundResult(cars));
    }

    public void printWinnerResult(List<Car> winners) {
        printStream.println(formatter.formatWinners(winners));
    }

    public void printGameResult(GameResult result) {
        printGameResultMessage();
        result.roundResults().forEach(this::printRoundResult);
        printWinnerResult(result.winners());
    }
}
