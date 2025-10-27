package racingcar.view.output;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.model.Car;
import racingcar.model.CarName;

class FormattableOutputViewTest {

    private FormattableOutputView view;

    @BeforeEach
    void setUp() {
        view = new FormattableOutputView();
    }

    @Test
    @DisplayName("게임 결과 메시지 포맷팅을 검증한다")
    void formatGameResultMessageTest() {
        // when
        String result = view.formatGameResultMessage();

        // then
        assertThat(result).isEqualTo(System.lineSeparator() + "실행 결과");
    }

    @Test
    @DisplayName("개별 자동차 위치 포맷팅을 검증한다")
    void formatCarPositionTest() {
        // given
        Car car = new Car(new CarName("pobi")).tryMove(5).tryMove(5).tryMove(5);

        // when
        String result = view.formatCarPosition(car);

        // then
        assertThat(result).isEqualTo("pobi : ---");
    }

    @Test
    @DisplayName("빈 자동차 리스트도 포맷팅한다")
    void formatEmptyListTest() {
        // given
        List<Car> emptyCars = List.of();

        // when
        String result = view.formatRoundResult(emptyCars);

        // then
        assertThat(result).isEqualTo(System.lineSeparator());
    }

    @Test
    @DisplayName("위치가 0인 자동차도 포맷팅한다")
    void formatZeroPositionTest() {
        // given
        Car car = new Car(new CarName("pobi"));

        // when
        String result = view.formatCarPosition(car);

        // then
        assertThat(result).isEqualTo("pobi : ");
    }

    @Test
    @DisplayName("라운드 결과 포맷팅을 검증한다")
    void formatRoundResultTest() {
        // given
        List<Car> cars = List.of(
                new Car(new CarName("pobi")).tryMove(5).tryMove(5),
                new Car(new CarName("woni")).tryMove(5)
        );

        // when
        String result = view.formatRoundResult(cars);

        // then
        assertThat(result)
                .contains("pobi : --")
                .contains("woni : -")
                .endsWith(System.lineSeparator());
    }


    @Test
    @DisplayName("우승자 포맷팅을 검증한다")
    void formatWinnerResultTest() {
        // given
        List<Car> winners = List.of(
                new Car(new CarName("pobi")),
                new Car(new CarName("jun"))
        );

        // when
        String result = view.formatWinnerResult(winners);

        // then
        assertThat(result).isEqualTo("최종 우승자 : pobi, jun");
    }

}
