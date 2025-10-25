package racingcar.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Game {
    private final List<Car> cars;
    private final int roundCount;
    private final Referee referee;

    private Game(List<Car> cars, int roundCount, Referee referee) {
        this.cars = cars;
        this.roundCount = roundCount;
        this.referee = referee;
    }

    public static Game init(List<CarName> carNames, int roundCount, Referee referee) {
        if (carNames.isEmpty() || Objects.isNull(carNames)) {
            throw new IllegalArgumentException("[ERROR] 자동차 목록이 비어있습니다.");
        }
        if (Objects.isNull(referee)) {
            throw new IllegalArgumentException("[ERROR] 심판이 비어있습니다.");
        }
        List<Car> cars = carNames.stream().map(Car::new).toList();
        return new Game(cars, roundCount, referee);
    }

    public List<Car> playRound(List<Car> current, RandomNumberGenerator randomNumberGenerator) {
        return current.stream()
                .map(car -> car.tryMove(randomNumberGenerator.pickNumberInRange(0, 9)))
                .toList();
    }

    public GameResult play(RandomNumberGenerator randomNumberGenerator) {
        List<Car> cars = this.cars;
        List<List<Car>> roundResults = new ArrayList<>();

        for (int i = 0; i < roundCount; i++) {
            cars = playRound(cars, randomNumberGenerator);
            roundResults.add(cars);
        }
        int maxPosition = referee.findMaxPosition(cars);
        List<Car> winners = referee.electWinners(cars, maxPosition);
        return new GameResult(roundResults, winners);

    }

}
