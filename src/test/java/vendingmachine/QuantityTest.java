package vendingmachine;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class QuantityTest {
    @Test
    void create_quantity_with_int() {
        final var value = 5;

        final var sut = Quantity.from(value);

        assertThat(sut).isEqualTo(Quantity.from(5));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    @DisplayName("0이하의 숫자로 생성시 예외를 발생한다.")
    void throw_exception_when_quantity_is_under_0(final int value) {
        assertThatThrownBy(() -> Quantity.from(value))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("수량에서 숫자만큼 뺀다")
    void minus_with_count() {
        final var sut = Quantity.from(5);

        final var result = sut.minus(4);

        assertThat(result).isEqualTo(Quantity.from(1));
    }

    @Test
    @DisplayName("수량보다 더 많은 숫자만큼 빼려할 시 예외를 발생한다")
    void throw_exception_when_over_count() {
        final var sut = Quantity.from(10);

        assertThatThrownBy(() -> sut.minus(15))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

