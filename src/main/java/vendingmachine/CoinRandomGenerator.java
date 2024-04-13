package vendingmachine;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.EnumMap;
import java.util.Map;

public class CoinRandomGenerator implements CoinGenerator {
    public Map<Coin, Integer> generate(Money money) {
        Map<Coin, Integer> coinBoard = initializeBoard();
        while (Coin.canExchange(money)) {
            final int randomMoney = Randoms.pickNumberInList(Coin.getExchangeList(money));
            final Coin coin = Coin.from(randomMoney);
            coinBoard.put(coin, coinBoard.get(coin) + 1);
            money = money.minus(new Money(randomMoney));
        }
        return coinBoard;
    }

    private static Map<Coin, Integer> initializeBoard() {
        Map<Coin, Integer> coinBoard = new EnumMap<>(Coin.class);
        for (final Coin coin : Coin.values()) {
            coinBoard.put(coin, 0);
        }
        return coinBoard;
    }
}
