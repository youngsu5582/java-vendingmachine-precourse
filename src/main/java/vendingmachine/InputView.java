package vendingmachine;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private InputView() {}

    public static int inputCoinMoney() {
        System.out.println("자판기가 보유하고 있는 금액을 입력해 주세요.");
        return inputNumber();
    }

    public static String inputProductsInfo() {
        System.out.println("상품명과 가격, 수량을 입력해 주세요.");
        return input();
    }

    public static int inputPurchaseMoney() {
        System.out.println("투입 금액을 입력해 주세요.");
        return inputNumber();
    }

    public static String inputPurchaseProductName() {
        System.out.println("구매할 상품명을 입력해 주세요.");
        return input();
    }

    private static int inputNumber() {
        return Parser.toInt(input());
    }

    private static String input() {
        return Console.readLine();
    }

}
