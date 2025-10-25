package racingcar.model;

import java.util.List;
import java.util.Objects;
import racingcar.view.GameOutputView;

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

    public List<Car> playRound(List<Car> current, RandomNumberGenerator randomNumberGenerator,
                               GameOutputView gameOutputView) {
        List<Car> cars = current.stream()
                .map(car -> car.tryMove(randomNumberGenerator.pickNumberInRange(0, 9)))
                .toList();
        gameOutputView.printRoundResult(cars);
        return cars;
    }

    public List<Car> play(RandomNumberGenerator randomNumberGenerator, GameOutputView gameOutputView) {
        List<Car> cars = this.cars;
        for (int i = 0; i < roundCount; i++) {
            cars = playRound(cars, randomNumberGenerator, gameOutputView);
        }
        int maxPosition = referee.findMaxPosition(cars);
        List<Car> winners = referee.electWinners(cars, maxPosition);
        new GameOutputView().printWinnerResult(winners);
        return cars;

    }

}
