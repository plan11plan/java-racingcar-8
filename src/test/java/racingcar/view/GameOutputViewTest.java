package racingcar.view;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.model.Car;
import racingcar.model.CarName;

class GameOutputViewTest {
    private PrintStream standardOut;
    private ByteArrayOutputStream captor;

    @BeforeEach
    void setUp() {
        standardOut = System.out;
        captor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(captor));
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    @DisplayName("라운드별 실행 결과를 올바르게 출력한다")
    void printRoundResultTest() {
        // given
        List<Car> cars = List.of(
                new Car(new CarName("pobi")).tryMove(5).tryMove(5),
                new Car(new CarName("woni")).tryMove(5)
        );

        // when
        new GameOutputView().printRoundResult(cars);

        // then
        String output = captor.toString();
        Assertions.assertThat(output).contains("실행 결과");
        Assertions.assertThat(output).contains("pobi : --");
        Assertions.assertThat(output).contains("woni : -");
    }

}
