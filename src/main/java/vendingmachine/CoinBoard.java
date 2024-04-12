package vendingmachine;

import java.util.EnumMap;
import java.util.Map;

public class CoinBoard {
    private final Map<Coin, Integer> value;

    public CoinBoard(final Map<Coin, Integer> value) {
        this.value = value;
    }

    public Map<Coin, Integer> change(Money money) {
        Map<Coin, Integer> changeCoins = new EnumMap<>(Coin.class);

        for (final Coin coin : Coin.values()) {
            final int count = calculateCoinCount(coin, money);
            changeCoins.put(coin, count);
            money = money.minus(coin.calculateMoneyWithCount(count));
        }
        updateValue(changeCoins);
        return changeCoins;
    }

    private void updateValue(Map<Coin, Integer> changeCoins) {
        changeCoins.forEach(((coin, count) ->
                this.value.computeIfPresent(coin, (key, existCount) -> existCount - count)));
    }

    private int calculateCoinCount(final Coin coin, final Money money) {
        return Math.min(coin.calculateMaxAmount(money), this.value.getOrDefault(coin, 0));
    }

}
