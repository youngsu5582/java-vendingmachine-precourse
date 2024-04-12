package vendingmachine;

import java.util.Arrays;
import java.util.List;

public class Product {
    private static final String INFO_SEPARATOR = ",";
    private final Money price;
    private final Name name;
    private Quantity quantity;

    public Product(final Name name, final Money price, final Quantity quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public static Product ofInfo(final String info) {
        final List<String> productInfos = Arrays.stream(info.split(INFO_SEPARATOR))
                                                .toList();
        validateSize(productInfos);
        return createProduct(productInfos);
    }

    private static Product createProduct(final List<String> value) {
        return new Product(
                Name.from(value.get(0)),
                Money.from(Parser.toInt(value.get(1))),
                Quantity.from(Parser.toInt(value.get(2))));
    }

    private static void validateSize(final List<String> value) {
        if (value.size() != 3) {
            throw new IllegalArgumentException("상품은 쉼표 두개로 분리해서 입력해주세요(EX: [콜라,1500,20])");
        }
    }

    public Name getName() {
        return this.name;
    }

    public boolean canPurchase(final Money money) {
        return money.isGreaterThan(this.price) && this.quantity.isNotZero();
    }

    public boolean isEqual(final String productName) {
        return this.name.isEqual(productName);
    }

    public Money purchase(final Money money) {
        if (money.isGreaterThan(this.price)) {
            this.quantity = this.quantity.minus();
            return money.minus(this.price);
        }
        throw new IllegalArgumentException(
                String.format("제품이 %d보다 더 비쌉니다.(제품 가격:%d)", money.value(), this.price.value()));
    }
}
