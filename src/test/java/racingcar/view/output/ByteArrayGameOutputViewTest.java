package racingcar.view.output;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.model.Car;
import racingcar.model.CarName;
import racingcar.model.GameResult;

class ByteArrayGameOutputViewTest {

    private ByteArrayOutputStream outputStream;
    private GameOutputView gameOutputView;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        gameOutputView = new GameOutputView(outputStream);
    }

    @AfterEach
    void tearDown() {
        System.out.println(outputStream.toString().trim());
    }

    @Test
    @DisplayName("ByteArrayOutputStream으로 라운드 결과를 출력한다")
    void printRoundResultTest() {
        // given
        List<Car> cars = List.of(
                new Car(new CarName("pobi")).tryMove(5).tryMove(5),
                new Car(new CarName("woni")).tryMove(5)
        );

        // when
        gameOutputView.printRoundResult(cars);

        // then
        String output = outputStream.toString();
        assertThat(output)
                .contains("pobi : --")
                .contains("woni : -")
                .endsWith(System.lineSeparator());
    }

    @Test
    @DisplayName("ByteArrayOutputStream으로 우승자를 출력한다")
    void printWinnerResultTest() {
        // given
        List<Car> winners = List.of(
                new Car(new CarName("pobi")),
                new Car(new CarName("jun"))
        );

        // when
        gameOutputView.printWinnerResult(winners);

        // then
        assertThat(outputStream.toString())
                .isEqualTo("최종 우승자 : pobi, jun" + System.lineSeparator());
    }

    @Test
    @DisplayName("ByteArrayOutputStream으로 게임 결과 메시지를 출력한다")
    void printGameResultMessageTest() {
        // when
        gameOutputView.printGameResultMessage();

        // then
        assertThat(outputStream.toString())
                .isEqualTo(System.lineSeparator() + "실행 결과" + System.lineSeparator());
    }

    @Test
    @DisplayName("ByteArrayOutputStream으로 전체 게임 결과를 출력한다")
    void printGameResultTest() {
        // given
        List<List<Car>> rounds = List.of(
                List.of(
                        new Car(new CarName("pobi")).tryMove(5),
                        new Car(new CarName("woni")).tryMove(3)
                ),
                List.of(
                        new Car(new CarName("pobi")).tryMove(5).tryMove(5),
                        new Car(new CarName("woni")).tryMove(3).tryMove(5)
                )
        );
        List<Car> winners = List.of(new Car(new CarName("pobi")).tryMove(5).tryMove(5));
        GameResult result = new GameResult(rounds, winners);

        // when
        gameOutputView.printGameResult(result);

        // then
        String output = outputStream.toString();
        assertThat(output)
                .contains("실행 결과")
                .contains("pobi : -")
                .contains("pobi : --")
                .contains("최종 우승자 : pobi");
    }

    @Test
    @DisplayName("출력된 바이트 크기를 검증한다")
    void verifyByteSizeTest() {
        // given
        List<Car> cars = List.of(new Car(new CarName("pobi")).tryMove(5));

        // when
        gameOutputView.printRoundResult(cars);

        // then
        assertThat(outputStream.size()).isGreaterThan(0);
    }

    @Test
    @DisplayName("여러 번 출력하면 내용이 누적된다")
    void multipleOutputTest() {
        // given
        List<Car> round1 = List.of(new Car(new CarName("pobi")).tryMove(5));
        List<Car> round2 = List.of(new Car(new CarName("pobi")).tryMove(5).tryMove(5));

        // when
        gameOutputView.printRoundResult(round1);
        gameOutputView.printRoundResult(round2);

        // then
        String output = outputStream.toString();
        assertThat(output)
                .contains("pobi : -")
                .contains("pobi : --");
    }

    @Test
    @DisplayName("단일 우승자를 출력한다")
    void printSingleWinnerTest() {
        // given
        List<Car> winners = List.of(new Car(new CarName("pobi")));

        // when
        gameOutputView.printWinnerResult(winners);

        // then
        assertThat(outputStream.toString()).isEqualTo("최종 우승자 : pobi" + System.lineSeparator());
    }

    @Test
    @DisplayName("빈 라운드 결과도 출력한다")
    void printEmptyRoundResultTest() {
        // given
        List<Car> emptyCars = List.of();

        // when
        gameOutputView.printRoundResult(emptyCars);

        // then
        assertThat(outputStream.toString()).isEqualTo(System.lineSeparator());
    }
}
