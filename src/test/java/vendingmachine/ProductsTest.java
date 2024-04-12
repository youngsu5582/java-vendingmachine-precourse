package vendingmachine;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ProductsTest {
    @Test
    void create_products_with_product_list() {
        final Product product1 = Product.ofInfo("콜라,1500,20");
        final Product product2 = Product.ofInfo("사이다,1500,20");

        assertThatCode(() -> new Products(List.of(product1, product2)))
                .doesNotThrowAnyException();
    }

    @Test
    void throw_exception_when_product_name_duplicate() {
        final Product product1 = Product.ofInfo("콜라,1500,20");
        final Product product2 = Product.ofInfo("콜라,1500,20");
        final var value = List.of(product1, product2);

        assertThatThrownBy(() -> new Products(value))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("[콜라,1500,20];[사이다,1000,10] 와 같은 형태의 정보를 통해 상품들을 생성한다")
    void create_products_with_info() {
        final String info = "[콜라,1500,20];[사이다,1000,10]";
        assertThatCode(() -> Products.ofInfo(info))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("")
    void some() {
        final Product product1 = Product.ofInfo("콜라,1500,20");
        final var sut = new Products(List.of(product1));

        final var money = sut.purchase(Money.from(2000), "콜라");

        assertThat(money).isEqualTo(Money.from(500));
    }

    @Test
    @DisplayName("없는 제품명을 입력하면 예외를 발생한다")
    void throw_exception_when_purchase_with_not_exist_product_name() {
        final Product product1 = Product.ofInfo("콜라,1500,20");
        final var sut = new Products(List.of(product1));
        final var money = Money.from(2000);

        assertThatThrownBy(() -> sut.purchase(money, "사이다"))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
