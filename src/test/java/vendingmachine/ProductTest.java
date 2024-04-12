package vendingmachine;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class ProductTest {
    @Test
    void create_product_with_name_money_quantity() {
        final Name name = Name.from("콜라");
        final Money money = Money.from(1500);
        final Quantity quantity = Quantity.from(10);

        assertThatCode(() -> new Product(name, money, quantity))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("쉼표로 분리되어 있는 정보를 통해 상품을 생성한다")
    void create_product_with_info() {
        final var productInfo = "콜라,1500,20";

        assertThatCode(() -> Product.ofInfo(productInfo))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"콜라.1500.20", "콜라,1500,20,국산"})
    @DisplayName("정보 포맷이 부적절할시, 예외를 발생한다")
    void throw_exception_when_info_is_not_valid(final String info) {
        assertThatThrownBy(() -> Product.ofInfo(info))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
