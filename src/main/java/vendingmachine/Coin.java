package vendingmachine;

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
        return Money.from(this.amount * count);
    }
}
