package racingcar.view.output;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import racingcar.model.Car;
import racingcar.model.CarName;

class ConsoleOutputViewFileTest {
    private PrintStream standardOut;
    private PrintStream fileOut;
    private Path outputFile;

    @BeforeEach
    void setUp(@TempDir Path tempDir) throws IOException {
        standardOut = System.out;
        outputFile = tempDir.resolve("output.txt");
        fileOut = new PrintStream(Files.newOutputStream(outputFile));
        System.setOut(fileOut);
    }

    @AfterEach
    void tearDown() throws IOException {
        fileOut.close();
        System.setOut(standardOut);

        System.out.println("=== 파일에 기록된 출력 ===");
        System.out.println(Files.readString(outputFile).trim());
        System.out.println("=========================");
    }

    @Test
    @DisplayName("파일로 라운드 결과를 출력한다")
    void printRoundResultToFileTest() throws IOException {
        // given
        List<Car> cars = List.of(
                new Car(new CarName("pobi")).tryMove(5).tryMove(5)
        );

        // when
        new ConsoleOutputView().printRoundResult(cars);
        fileOut.flush();

        // then
        String content = Files.readString(outputFile);
        assertThat(content).contains("pobi : --");
    }

    @Test
    @DisplayName("파일로 우승자를 출력한다")
    void printWinnerToFileTest() throws IOException {
        // given
        List<Car> winners = List.of(new Car(new CarName("pobi")));

        // when
        new ConsoleOutputView().printWinnerResult(winners);
        fileOut.flush();

        // then
        assertThat(outputFile).exists();
        String content = Files.readString(outputFile);
        assertThat(content).isEqualTo("최종 우승자 : pobi\n");
    }

    @Test
    @DisplayName("파일 크기를 검증한다")
    void printResultFileSizeTest() throws IOException {
        // given
        List<Car> cars = List.of(
                new Car(new CarName("pobi")).tryMove(5),
                new Car(new CarName("woni")).tryMove(5)
        );

        // when
        new ConsoleOutputView().printRoundResult(cars);
        fileOut.flush();

        // then
        long fileSize = Files.size(outputFile);
        assertThat(fileSize).isGreaterThan(0);
    }
}
