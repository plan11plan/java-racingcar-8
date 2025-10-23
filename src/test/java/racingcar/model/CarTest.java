package racingcar.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class CarTest {

    @DisplayName("자동차를 생성한다.")
    @Test
    void create_car() {
        // given
        CarName carName = new CarName("12345");

        // when
        Car car = new Car(carName);

        // then
        assertThat(car).isNotNull();
        assertThat(car.name()).isSameAs(carName);
        assertThat(car.position()).isZero();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("자동차 생성 실패 - 빈 이름")
    void create_car_fail_blank(String input) {
        assertThatThrownBy(() -> new Car(new CarName(input)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("자동차 생성 실패 - 5글자 초과")
    void create_car_fail_length() {
        assertThatThrownBy(() -> new Car(new CarName("123456")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이동 기준 값보다 작으면 움직이지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    void tryMove_no_move(int input) {
        // given
        CarName carName = new CarName("12345");
        Car car = new Car(carName);

        // when
        Car movedCar = car.tryMove(input);

        // then
        Assertions.assertThat(movedCar.position()).isEqualTo(0);
    }

    @DisplayName("이동 기준 값 이상이면 포함되면 전진한다.")
    @ParameterizedTest
    @ValueSource(ints = {4, 5, 6, 7, 8, 9})
    void tryMove_do_move(int input) {
        // given
        CarName carName = new CarName("12345");
        Car car = new Car(carName);

        // when
        Car movedCar = car.tryMove(input);

        // then
        Assertions.assertThat(movedCar.position()).isEqualTo(1);
    }

    @DisplayName("연속 전진이 가능하다")
    @ParameterizedTest
    @ValueSource(ints = {4})
    void tryMove_continue(int input) {
        // given
        CarName carName = new CarName("12345");
        Car car = new Car(carName);

        // when
        Car movedCar = car.tryMove(input);
        movedCar = movedCar.tryMove(input);

        // then
        Assertions.assertThat(movedCar.position()).isEqualTo(2);
    }
}
