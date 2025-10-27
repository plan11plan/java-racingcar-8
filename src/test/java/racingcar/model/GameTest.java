package racingcar.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {


    @DisplayName("게임을 초기화한다.")
    @Test
    public void init() {
        // given
        List<CarName> carNames = List.of(new CarName("pobi"), new CarName("woni"), new CarName("jun"));
        RoundCount roundCount = new RoundCount(3);
        Referee referee = new Referee();

        // when
        Game game = Game.init(carNames, roundCount, referee);

        // then
        Assertions.assertNotNull(game);
    }

    @DisplayName("게임 초기화 실패 - 자동차 이름 빈 값 요소")
    @Test
    public void init_fail_when_car_empty() {
        // given
        List<CarName> carNames = List.of();
        RoundCount roundCount = new RoundCount(3);
        Referee referee = new Referee();

        // expect
        assertThatThrownBy(() -> Game.init(carNames, roundCount, referee))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("게임 초기화 실패 - 심판 빈 값 요소")
    @Test
    public void init_fail_when_referee_empty() {
        // given
        List<CarName> carNames = List.of(new CarName("pobi"), new CarName("woni"), new CarName("jun"));
        RoundCount roundCount = new RoundCount(3);
        Referee referee = null;

        // expect
        assertThatThrownBy(() -> Game.init(carNames, roundCount, referee))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("한 라운드를 진행한다 - 랜덤숫자생성기가 4를 반환하면 모두 한 칸 전진")
    @Test
    void playRound_moves_when_rng_return_4() {
        // given
        List<CarName> carNames = List.of(new CarName("pobi"), new CarName("woni"), new CarName("jun"));
        Referee referee = new Referee();
        RoundCount roundCount = new RoundCount(3);

        Game game = Game.init(carNames, roundCount, referee);
        List<Car> cars = carNames.stream().map(Car::new).toList();
        RandomNumberGenerator rng = (startInclusive, endInclusive) -> 4;

        // when
        List<Car> playedCars = game.playRound(cars, rng);

        // then
        assertThat(playedCars).hasSize(cars.size());
        assertThat(playedCars.stream().mapToInt(Car::position).boxed().toList())
                .containsExactly(1, 1, 1);
        assertThat(playedCars.stream().map(c -> c.name().name()).toList())
                .containsExactly("pobi", "woni", "jun");
    }

    @DisplayName("한 라운드를 진행한다 - 랜덤숫자생성기가 3을 반환하면 모두 정지")
    @Test
    void playRound_stays_when_rng_return_3() {
        // given
        List<CarName> carNames = List.of(new CarName("pobi"), new CarName("woni"), new CarName("jun"));
        RoundCount roundCount = new RoundCount(1);
        Game game = Game.init(carNames, roundCount, new Referee());
        List<Car> cars = carNames.stream().map(Car::new).toList();
        RandomNumberGenerator rng = (startInclusive, endInclusive) -> 3;

        // when
        List<Car> playedCars = game.playRound(cars, rng);

        // then
        assertThat(playedCars.stream().mapToInt(Car::position).sum()).isEqualTo(0);
    }

    @DisplayName("한 라운드를 진행해도 자동차의 순서는 유지된다")
    @Test
    void playRound_preserves_order() {
        // given
        List<CarName> carNames = List.of(new CarName("pobi"), new CarName("woni"), new CarName("jun"));
        RoundCount roundCount = new RoundCount(1);
        Game game = Game.init(carNames, roundCount, new Referee());
        List<Car> cars = carNames.stream().map(Car::new).toList();
        RandomNumberGenerator rng = (startInclusive, endInclusive) -> 4;

        // when
        List<Car> played = game.playRound(cars, rng);

        // then
        assertThat(played.stream().map(c -> c.name().name()).toList())
                .containsExactly("pobi", "woni", "jun");
    }

    @DisplayName("전체 게임을 실행한다")
    @Test
    void play() {
        // given
        List<CarName> carNames = List.of(new CarName("pobi"), new CarName("woni"), new CarName("jun"));
        RoundCount roundCount = new RoundCount(2);
        Game game = Game.init(carNames, roundCount, new Referee());
        RandomNumberGenerator rng = (startInclusive, endInclusive) -> 4;

        // when
        GameResult result = game.play(rng);

        //then
        org.assertj.core.api.Assertions.assertThat(result.winners()).hasSize(3);


    }

}
