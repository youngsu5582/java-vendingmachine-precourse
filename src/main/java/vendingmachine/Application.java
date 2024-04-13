package vendingmachine;

public class Application {
    public static void main(String[] args) {
        try {
            Money coinMoney = Money.from(InputView.inputCoinMoney());
            final CoinBoard coinBoard = new CoinBoard(new CoinRandomGenerator().generate(coinMoney));
            OutputView.printCoinBoard(coinBoard.toMap());
            final Products products = Products.ofInfo(InputView.inputProductsInfo());

            final VendingMachine vendingMachine = new VendingMachine(coinBoard, products);

            Money purchaseMoney = Money.from(InputView.inputPurchaseMoney());

            while (vendingMachine.canPurchase(purchaseMoney)) {
                OutputView.printMoney(purchaseMoney.value());
                final String productName = InputView.inputPurchaseProductName();
                purchaseMoney = vendingMachine.purchase(purchaseMoney, productName);
            }
            OutputView.printMoney(purchaseMoney.value());
            OutputView.printChange(vendingMachine.change(purchaseMoney));

        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
        }

    }
}
