package racingcar.view.output;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.model.Car;
import racingcar.model.CarName;

public class GameResultFormatter {

    public String formatGameResultHeader() {
        return System.lineSeparator() + "실행 결과";
    }

    public String formatRoundResult(List<Car> cars) {
        StringBuilder sb = new StringBuilder();
        for (Car car : cars) {
            sb.append(formatCarPosition(car)).append(System.lineSeparator());
        }
        sb.append(System.lineSeparator());
        return sb.toString();
    }

    public String formatCarPosition(Car car) {
        return car.name().name() + " : " + "-".repeat(car.position());
    }

    public String formatWinners(List<Car> winners) {
        String winnerNames = winners.stream()
                .map(Car::name)
                .map(CarName::name)
                .collect(Collectors.joining(", "));
        return "최종 우승자 : " + winnerNames;
    }
}
