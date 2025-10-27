package racingcar.view.output;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.model.Car;
import racingcar.model.CarName;

class GameResultFormatterTest {

    private GameResultFormatter formatter;

    @BeforeEach
    void setUp() {
        formatter = new GameResultFormatter();
    }

    @Test
    @DisplayName("개별 자동차 위치를 포맷팅한다")
    void formatCarPositionTest() {
        // given
        Car car = new Car(new CarName("pobi")).tryMove(5).tryMove(5);

        // when
        String result = formatter.formatCarPosition(car);

        // then
        assertThat(result).isEqualTo("pobi : --");
    }

    @Test
    @DisplayName("라운드 결과를 포맷팅한다")
    void formatRoundResultTest() {
        // given
        List<Car> cars = List.of(
                new Car(new CarName("pobi")).tryMove(5),
                new Car(new CarName("woni")).tryMove(5)
        );

        // when
        String result = formatter.formatRoundResult(cars);

        // then
        assertThat(result).isEqualTo("pobi : -\nwoni : -\n\n");
    }

    @Test
    @DisplayName("우승자 목록을 포맷팅한다")
    void formatWinnersTest() {
        // given
        List<Car> winners = List.of(
                new Car(new CarName("pobi")),
                new Car(new CarName("jun"))
        );

        // when
        String result = formatter.formatWinners(winners);

        // then
        assertThat(result).isEqualTo("최종 우승자 : pobi, jun");
    }

    @Test
    @DisplayName("게임 결과 헤더를 포맷팅한다")
    void formatGameResultHeaderTest() {
        // when
        String result = formatter.formatGameResultHeader();

        // then
        assertThat(result).isEqualTo("\n실행 결과");
    }

    @Test
    @DisplayName("단일 우승자를 포맷팅한다")
    void formatSingleWinnerTest() {
        // given
        List<Car> winners = List.of(new Car(new CarName("pobi")));

        // when
        String result = formatter.formatWinners(winners);

        // then
        assertThat(result).isEqualTo("최종 우승자 : pobi");
    }

    @Test
    @DisplayName("여러 우승자를 쉼표로 구분한다")
    void formatMultipleWinnersTest() {
        // given
        List<Car> winners = List.of(
                new Car(new CarName("pobi")),
                new Car(new CarName("jun")),
                new Car(new CarName("honux"))
        );

        // when
        String result = formatter.formatWinners(winners);

        // then
        assertThat(result).isEqualTo("최종 우승자 : pobi, jun, honux");
    }

    @Test
    @DisplayName("위치가 0인 자동차도 포맷팅한다")
    void formatZeroPositionTest() {
        // given
        Car car = new Car(new CarName("pobi"));

        // when
        String result = formatter.formatCarPosition(car);

        // then
        assertThat(result).isEqualTo("pobi : ");
    }

    @Test
    @DisplayName("빈 자동차 리스트도 포맷팅한다")
    void formatEmptyListTest() {
        // given
        List<Car> emptyCars = List.of();

        // when
        String result = formatter.formatRoundResult(emptyCars);

        // then
        assertThat(result).isEqualTo("\n");
    }

    @Test
    @DisplayName("포지션에 따른 대시 개수가 정확하다")
    void formatDashCountTest() {
        // given
        Car car1 = new Car(new CarName("car1")).tryMove(5);
        Car car2 = new Car(new CarName("car2")).tryMove(5).tryMove(5);
        Car car3 = new Car(new CarName("car3")).tryMove(5).tryMove(5).tryMove(5);

        // when & then
        assertThat(formatter.formatCarPosition(car1)).isEqualTo("car1 : -");
        assertThat(formatter.formatCarPosition(car2)).isEqualTo("car2 : --");
        assertThat(formatter.formatCarPosition(car3)).isEqualTo("car3 : ---");
    }
}
