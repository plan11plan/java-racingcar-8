package racingcar.model;

public class Car {
    private final int MOVE_CRITERIA = 4;
    private final CarName name;
    private final int position;

    public Car(CarName name) {
        this.name = name;
        this.position = 0;
    }

    private Car(CarName name, int position) {
        this.name = name;
        this.position = position;
    }

    public Car tryMove(int number) {
        if (number < MOVE_CRITERIA) {
            return this;
        }
        return this.move();
    }

    private Car move() {
        return new Car(this.name, this.position + 1);
    }

    public CarName name() {
        return name;
    }

    public int position() {
        return position;
    }
}
