package racingcar.view.output;

import java.util.List;
import racingcar.model.Car;
import racingcar.model.CarName;
import racingcar.model.GameResult;

public class ConsoleOutputView implements GameOutputView {

    @Override
    public void printGameResult(GameResult result) {
        printGameResultMessage();

        result.roundResults()
                .forEach(this::printRoundResult);

        printWinnerResult(result.winners());

    }

    @Override
    public void printGameResultMessage() {
        System.out.println();
        System.out.println("실행 결과");
    }

    @Override
    public void printRoundResult(List<Car> cars) {
        for (Car car : cars) {
            System.out.println(car.name().name() + " : " + "-".repeat(car.position()));
        }
        System.out.println();
    }

    @Override
    public void printWinnerResult(List<Car> cars) {
        List<String> winnerNames = cars.stream()
                .map(Car::name)
                .map(CarName::name)
                .toList();

        String winnersText = String.join(", ", winnerNames);
        System.out.println("최종 우승자 : " + winnersText);
    }

}
