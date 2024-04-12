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

    @Test
    @DisplayName("금액을 통해 제품을 구매한다.")
    void purchase_product() {
        final var money = Money.from(3000);
        final var sut = Product.ofInfo("콜라,1500,20");

        final var result = sut.purchase(money);

        assertThat(result).isEqualTo(Money.from(1500));
    }

    @Test
    @DisplayName("금액이 제품 금액보다 작으면 예외를 발생한다")
    void throw_exception_when_money_is_under_price() {
        final var money = Money.from(500);
        final var sut = Product.ofInfo("콜라,1500,20");

        assertThatThrownBy(() -> sut.purchase(money))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("제품의 개수가 0이면 예외를 발생한다")
    void throw_exception_when_quantity_is_zero() {
        final var money = Money.from(3000);
        final var sut = Product.ofInfo("콜라,1500,1");

        sut.purchase(money);

        assertThatThrownBy(() -> sut.purchase(money))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
