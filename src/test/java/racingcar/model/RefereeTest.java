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

}
