package racingcar.model;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RefereeTest {

    @DisplayName("최대 위치를 찾는다. - 최대 위치가 하나인 경우")
    @Test
    void findMaxPosition_when_one_case() {
        // given
        List<Car> cars = List.of(
                new Car(new CarName("pobi")).tryMove(5).tryMove(5),
                new Car(new CarName("woni")).tryMove(5),
                new Car(new CarName("jun"))
        );
        Referee referee = new Referee(cars);

        // when
        int maxPosition = referee.findMaxPosition();

        // then
        Assertions.assertThat(maxPosition).isEqualTo(2);
    }

    @DisplayName("최대 위치를 찾는다. - 최대 위치가 여러개인 경우")
    @Test
    void findMaxPosition_when_multiple_case() {
        // given
        List<Car> cars = List.of(
                new Car(new CarName("pobi")).tryMove(5).tryMove(5),
                new Car(new CarName("woni")).tryMove(5).tryMove(5),
                new Car(new CarName("jun"))
        );
        Referee referee = new Referee(cars);

        // when
        int maxPosition = referee.findMaxPosition();

        // then
        Assertions.assertThat(maxPosition).isEqualTo(2);
    }

    @DisplayName("최대 위치를 찾는다. - 한 대인 경우")
    @Test
    void findMaxPosition_when_one_car() {
        // given
        List<Car> cars = List.of(
                new Car(new CarName("pobi")).tryMove(5).tryMove(5)
        );
        Referee referee = new Referee(cars);

        // when
        int maxPosition = referee.findMaxPosition();

        // then
        Assertions.assertThat(maxPosition).isEqualTo(2);
    }

    @DisplayName("우승자를 선출한다. - 단독")
    @Test
    void electWinners_only_one() {
        // given
        List<Car> cars = List.of(
                new Car(new CarName("pobi")).tryMove(5).tryMove(5),
                new Car(new CarName("woni")).tryMove(5),
                new Car(new CarName("jun"))
        );
        Referee referee = new Referee(cars);
        int maxPosition = referee.findMaxPosition();

        // when
        List<Car> winners = referee.electWinners(maxPosition);

        // then
        Assertions.assertThat(winners.size()).isEqualTo(1);
        Assertions.assertThat(winners.getFirst().name().name()).isEqualTo("pobi");
    }

    @DisplayName("우승자를 선출한다. - 공동")
    @Test
    void electWinners_joint_winner() {
        // given
        List<Car> cars = List.of(
                new Car(new CarName("pobi")).tryMove(5).tryMove(5),
                new Car(new CarName("woni")).tryMove(5).tryMove(5),
                new Car(new CarName("jun"))
        );
        Referee referee = new Referee(cars);
        int maxPosition = referee.findMaxPosition();

        // when
        List<Car> winners = referee.electWinners(maxPosition);

        // then
        Assertions.assertThat(winners.size()).isEqualTo(2);
        Assertions.assertThat(winners.getFirst().name().name()).isEqualTo("pobi");
        Assertions.assertThat(winners.getLast().name().name()).isEqualTo("woni");

    }

    @DisplayName("우승자를 선출한다. - ㅁㅗㄷㅜ")
    @Test
    void electWinners_all_winner() {
        // given
        List<Car> cars = List.of(
                new Car(new CarName("pobi")).tryMove(5).tryMove(5),
                new Car(new CarName("woni")).tryMove(5).tryMove(5),
                new Car(new CarName("jun")).tryMove(5).tryMove(5)
        );
        Referee referee = new Referee(cars);
        int maxPosition = referee.findMaxPosition();

        // when
        List<Car> winners = referee.electWinners(maxPosition);

        // then
        Assertions.assertThat(winners.size()).isEqualTo(3);
        Assertions.assertThat(winners.getFirst().name().name()).isEqualTo("pobi");
        Assertions.assertThat(winners.getLast().name().name()).isEqualTo("jun");

    }

    @DisplayName("우승자를 선출한다. - 초기화 순서다.")
    @Test
    void electWinners_has_input_order() {
        // given
        List<Car> cars = List.of(
                new Car(new CarName("pobi")).tryMove(5).tryMove(5),
                new Car(new CarName("woni")).tryMove(5).tryMove(5),
                new Car(new CarName("jun")).tryMove(5).tryMove(5)
        );
        Referee referee = new Referee(cars);
        int maxPosition = referee.findMaxPosition();

        // when
        List<Car> winners = referee.electWinners(maxPosition);

        // then
        Assertions.assertThat(winners.size()).isEqualTo(3);
        Assertions.assertThat(winners.getFirst().name().name()).isEqualTo("pobi");
        Assertions.assertThat(winners.getLast().name().name()).isEqualTo("jun");

    }

}
