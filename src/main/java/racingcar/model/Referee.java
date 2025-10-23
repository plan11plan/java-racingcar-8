package racingcar.model;

import java.util.List;

public class Referee {
    List<Car> cars;

    public Referee(List<Car> cars) {
        this.cars = cars;
    }

    public int findMaxPosition() {
        return cars.stream()
                .map(Car::position)
                .mapToInt(Integer::intValue)
                .max()
                .getAsInt();
    }
}
