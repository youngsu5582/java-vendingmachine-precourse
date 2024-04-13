package vendingmachine;

import java.util.Arrays;
import java.util.List;

public enum Coin {
    COIN_500(500),
    COIN_100(100),
    COIN_50(50),
    COIN_10(10);

    private final int amount;

    Coin(final int amount) {
        this.amount = amount;
    }

    public int calculateMaxAmount(final Money money) {
        return money.value() / this.amount;
    }

    public Money calculateMoneyWithCount(final int count) {
        return new Money(this.amount * count);
    }

    public int getAmount() {
        return amount;
    }

    public static List<Integer> getExchangeList(final Money money) {
        return Arrays.stream(values())
                     .filter(coin -> coin.amount <= money.value())
                     .map(Coin::getAmount)
                     .toList();
    }

    public static boolean canExchange(final Money money) {
        return Arrays.stream(values())
                     .anyMatch(coin -> coin.amount <= money.value());
    }

    public static Coin from(final int value) {
        for (Coin coin : values()) {
            if (coin.amount == value) {
                return coin;
            }
        }
        throw new IllegalArgumentException(String.format("%d는 없는 동전 금액입니다.", value));
    }

}
