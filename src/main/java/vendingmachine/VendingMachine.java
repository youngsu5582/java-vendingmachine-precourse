package vendingmachine;

import java.util.Map;

public class VendingMachine {
    private final CoinBoard coinBoard;
    private final Products products;

    public VendingMachine(final CoinBoard coinBoard, final Products products) {
        this.coinBoard = coinBoard;
        this.products = products;
    }

    public boolean canPurchase(final Money money) {
        return products.canPurchase(money);
    }

    public Money purchase(final Money money, final String productName) {
        return products.purchase(money, productName);
    }

    public Map<Coin, Integer> change(final Money money) {
        return coinBoard.change(money);
    }
}
