package vendingmachine;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CoinTest {
    @Test
    void calculate_max_count_with_money() {
        Coin coin = Coin.COIN_500;

        assertThat(coin.calculateMaxAmount(Money.from(2500))).isEqualTo(5);
    }

    @Test
    void calculate_money_with_count() {
        Coin coin = Coin.COIN_50;

        assertThat(coin.calculateMoneyWithCount(5)).isEqualTo(Money.from(250));
    }
}
