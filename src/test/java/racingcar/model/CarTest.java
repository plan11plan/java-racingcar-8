package racingcar.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

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
}
