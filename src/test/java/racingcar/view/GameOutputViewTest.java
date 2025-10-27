package racingcar.view;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
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
    private OutputStream captor;

    @BeforeEach
    void setUp() {
        this.standardOut = System.out;
        this.captor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(this.captor));
    }

    @AfterEach
    void tearDown() {
        System.setOut(this.standardOut);
        System.out.println(this.captor.toString().trim());
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
        Assertions.assertThat(output).contains("pobi : --");
        Assertions.assertThat(output).contains("woni : -");
    }

    @Test
    @DisplayName("최종 우승자를 올바르게 출력한다")
    void printWinnerResultTest() {
        // given
        List<Car> winners = List.of(
                new Car(new CarName("pobi")),
                new Car(new CarName("jun"))
        );
        String expectedOutput = "최종 우승자 : pobi, jun\n";

        // when
        new GameOutputView().printWinnerResult(winners);

        // then
        String output = captor.toString();
        Assertions.assertThat(output).isEqualTo(expectedOutput);
    }

}
