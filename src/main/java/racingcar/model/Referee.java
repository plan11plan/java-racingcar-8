package racingcar.model;

import java.util.List;

public class Referee {

    public int findMaxPosition(List<Car> cars) {
        return cars.stream()
                .map(Car::position)
                .mapToInt(Integer::intValue)
                .max()
                .getAsInt();
    }

    public List<Car> electWinners(List<Car> cars, int maxPosition) {
        return cars.stream()
                .filter(car -> car.position() == maxPosition)
                .toList();
    }
}
