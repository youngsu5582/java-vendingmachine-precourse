package vendingmachine;

import java.util.Map;

public class OutputView {
    private OutputView() {}

    public static void printError(String errorMessage) {
        System.out.println("[ERROR] " + errorMessage);
    }

    public static void printCoinBoard(Map<Coin, Integer> coinBoard) {
        System.out.println("자판기가 보유한 동전");
        for (final Coin coin : Coin.values()) {
            System.out.println(formatCoinInfo(coin.getAmount(), coinBoard.getOrDefault(coin, 0)));
        }
    }

    public static void printMoney(final Integer money) {
        System.out.println("투입 금액: " + money + "원");
    }


    public static void printChange(Map<Coin, Integer> changeBoard) {
        System.out.println("잔돈");
        for (final Coin coin : Coin.values()) {
            if (changeBoard.containsKey(coin)) {
                System.out.println(formatCoinInfo(coin.getAmount(), changeBoard.get(coin)));
            }
        }
    }

    private static String formatCoinInfo(final int coinAmount, final int count) {
        final StringBuilder sb = new StringBuilder();
        sb.append(coinAmount)
          .append("원")
          .append(" - ")
          .append(count)
          .append("개");
        return sb.toString();
    }


}
