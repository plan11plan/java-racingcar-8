package racingcar.view.output;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.model.Car;
import racingcar.model.CarName;
import racingcar.model.GameResult;

public class FormattableOutputView implements GameOutputView {

    // 포맷팅 로직
    String formatGameResultMessage() {
        return System.lineSeparator() + "실행 결과";
    }

    String formatRoundResult(List<Car> cars) {
        StringBuilder sb = new StringBuilder();
        for (Car car : cars) {
            sb.append(formatCarPosition(car)).append(System.lineSeparator());
        }
        sb.append(System.lineSeparator());
        return sb.toString();
    }

    String formatCarPosition(Car car) {
        return car.name().name() + " : " + "-".repeat(car.position());
    }

    String formatWinnerResult(List<Car> winners) {
        String winnerNames = winners.stream()
                .map(Car::name)
                .map(CarName::name)
                .collect(Collectors.joining(", "));
        return "최종 우승자 : " + winnerNames;
    }

    @Override
    public void printGameResultMessage() {
        System.out.println(formatGameResultMessage());
    }

    @Override
    public void printRoundResult(List<Car> cars) {
        System.out.print(formatRoundResult(cars));
    }

    @Override
    public void printWinnerResult(List<Car> winners) {
        System.out.println(formatWinnerResult(winners));
    }

    @Override
    public void printGameResult(GameResult result) {
        printGameResultMessage();
        result.roundResults().forEach(this::printRoundResult);
        printWinnerResult(result.winners());
    }
}
