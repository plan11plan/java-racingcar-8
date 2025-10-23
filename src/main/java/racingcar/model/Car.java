package racingcar.model;

public class Car {
    private final CarName name;
    private final int position;

    public Car(CarName name) {
        this.name = name;
        this.position = 0;
    }

    public CarName name() {
        return name;
    }

    public int position() {
        return position;
    }
}
