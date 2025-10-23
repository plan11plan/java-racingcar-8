package racingcar.model;

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
        int roundCount = 3;
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
        int roundCount = 3;
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
        int roundCount = 3;
        Referee referee = null;

        // expect
        assertThatThrownBy(() -> Game.init(carNames, roundCount, referee))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
