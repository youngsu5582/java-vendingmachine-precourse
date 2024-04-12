package vendingmachine;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoneyTest {
    @Test
    void create_money_with_int() {
        final var value = 5000;

        final var sut = Money.from(value);

        assertThat(sut).isEqualTo(Money.from(5000));
    }

    @ParameterizedTest
    @ValueSource(ints = {99, -1, 0})
    @DisplayName("100미만의 숫자로 생성시 예외를 발생한다.")
    void throw_exception_when_money_is_under_100(final int value) {
        assertThatThrownBy(() -> Money.from(value))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {104, 301})
    @DisplayName("10으로 나눠지지 않는 숫자로 생성시 예외를 발생한다.")
    void throw_exception_when_money_is_not_divide_10(final int value) {
        assertThatThrownBy(() -> Money.from(value))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
