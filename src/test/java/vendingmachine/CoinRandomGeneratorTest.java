package vendingmachine;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CoinRandomGeneratorTest {
    @Test
    @DisplayName("금액만큼 동전들을 무작위로 생성한다.")
    void generate_random_coin() {
        final var coinBoard = new CoinRandomGenerator().generate(Money.from(500));
        Money money = Money.ZERO;
        for (Coin coin : Coin.values()) {
            money = money.plus(coin.calculateMoneyWithCount(coinBoard.get(coin)));
        }
        assertThat(money).isEqualTo(Money.from(500));
    }
}
