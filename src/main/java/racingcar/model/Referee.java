package racingcar.model;

import java.util.List;

public class Referee {
    private final List<Car> cars;

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

    public List<Car> electWinners(int maxPosition) {
        return this.cars.stream()
                .filter(car -> car.position() == maxPosition)
                .toList();
    }
}
