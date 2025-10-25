package racingcar.model;

import java.util.List;

public record GameResult(
        List<List<Car>> roundResults,
        List<Car> winners
) {
}
