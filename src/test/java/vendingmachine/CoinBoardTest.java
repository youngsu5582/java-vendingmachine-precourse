package vendingmachine;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class CoinBoardTest {
    @Test
    @DisplayName("잔돈으로 교환해준다")
    void exchange_money() {
        final Map<Coin, Integer> changeCoins = new EnumMap<>(Coin.class);
        changeCoins.put(Coin.COIN_500, 2);
        changeCoins.put(Coin.COIN_100, 5);
        final var sut = new CoinBoard(changeCoins);

        final var result = sut.change(Money.from(500));

        assertThat(result).containsEntry(Coin.COIN_500, 1);
    }

    @Test
    @DisplayName("금잔돈을 반환할 수 없는 경우 잔돈으로 반환할 수 있는 금액만 반환한다.")
    void exchange_money_with_can_change() {
        final Map<Coin, Integer> changeCoins = new EnumMap<>(Coin.class);
        changeCoins.put(Coin.COIN_100, 4);
        changeCoins.put(Coin.COIN_50, 1);

        final var sut = new CoinBoard(changeCoins);

        final var result = sut.change(Money.from(500));

        assertThat(result).containsEntry(Coin.COIN_100, 4)
                          .containsEntry(Coin.COIN_50, 1);
    }

    @Test
    @DisplayName("최소 개수의 동전으로 잔돈을 돌려준다")
    void exchange_money_with_minimum_coin_count() {
        final Map<Coin, Integer> changeCoins = new EnumMap<>(Coin.class);
        changeCoins.put(Coin.COIN_100, 10);
        changeCoins.put(Coin.COIN_500, 2);

        final var sut = new CoinBoard(changeCoins);

        final var result = sut.change(Money.from(1000));

        assertThat(result).containsEntry(Coin.COIN_500, 2)
                          .containsEntry(Coin.COIN_100, 0);
    }

}
