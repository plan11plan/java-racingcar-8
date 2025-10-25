package racingcar.view;

import java.util.List;
import racingcar.model.Car;

public class GameOutputView {

    public void printRoundResult(List<Car> cars) {
        System.out.println();
        System.out.println("실행 결과");
        for (Car car : cars) {
            System.out.println(car.name().name() + " : " + "-".repeat(car.position()));
        }
        System.out.println();
    }

}
