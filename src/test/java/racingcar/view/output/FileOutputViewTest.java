package racingcar.view.output;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.FileOutputStream;
import java.io.IOException;
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

class FileOutputViewTest {
    private GameOutputView gameOutputView;
    private Path outputFile;

    @BeforeEach
    void setUp(@TempDir Path tempDir) throws IOException {
        outputFile = tempDir.resolve("output.txt");
        FileOutputStream fos = new FileOutputStream(outputFile.toFile());
        gameOutputView = new GameOutputView(fos);
    }

    @AfterEach
    void tearDown() throws IOException {
        System.out.println(Files.readString(outputFile).trim());

    }

    @Test
    @DisplayName("파일로 라운드 결과를 출력한다")
    void printRoundResultToFileTest() throws IOException {
        // given
        List<Car> cars = List.of(
                new Car(new CarName("pobi")).tryMove(5).tryMove(5)
        );

        // when
        gameOutputView.printRoundResult(cars);

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
        gameOutputView.printWinnerResult(winners);

        // then
        assertThat(outputFile).exists();
        String content = Files.readString(outputFile);
        assertThat(content).isEqualTo("최종 우승자 : pobi" + System.lineSeparator());
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
        gameOutputView.printRoundResult(cars);

        // then
        long fileSize = Files.size(outputFile);
        assertThat(fileSize).isGreaterThan(0);
    }
}
