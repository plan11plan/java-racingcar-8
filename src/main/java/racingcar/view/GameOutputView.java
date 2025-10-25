package racingcar.view;

import java.util.List;
import racingcar.model.Car;
import racingcar.model.CarName;

public class GameOutputView {

    public void printRoundResult(List<Car> cars) {
        System.out.println();
        System.out.println("실행 결과");
        for (Car car : cars) {
            System.out.println(car.name().name() + " : " + "-".repeat(car.position()));
        }
        System.out.println();
    }

    public void printWinnerResult(List<Car> cars) {
        List<String> winnerNames = cars.stream()
                .map(Car::name)
                .map(CarName::name)
                .toList();

        String winnersText = String.join(", ", winnerNames);
        System.out.println("최종 우승자 : " + winnersText);
    }
}
