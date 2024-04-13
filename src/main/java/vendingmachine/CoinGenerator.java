package vendingmachine;

import java.util.Map;

public interface CoinGenerator {
    public Map<Coin, Integer> generate(Money money);
}
