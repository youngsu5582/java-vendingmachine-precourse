package vendingmachine;

public record Money(int value) {
    public static final Money ZERO = new Money(0);
    private static final int MINIMUM_VALUE = 100;
    private static final int DIVIDE_UNIT = 10;

    public static Money from(int value) {
        validateMoney(value);
        return new Money(value);
    }

    private static void validateMoney(final int value) {
        validateMinimum(value);
        validateDivide(value);
    }

    private static void validateMinimum(final int value) {
        if (value < MINIMUM_VALUE) {
            throw new IllegalArgumentException(String.format("%d보다 작습니다.", MINIMUM_VALUE));
        }
    }

    private static void validateDivide(final int value) {
        if (value % DIVIDE_UNIT != 0) {
            throw new IllegalArgumentException(String.format("금액은 %d 로 나누어져야 합니다.", DIVIDE_UNIT));
        }
    }

    public Money minus(Money other) {
        return new Money(this.value - other.value);
    }

    public Money plus(Money other) {
        return new Money(this.value + other.value);
    }

    public boolean isGreaterThan(Money other) {
        return this.value > other.value;
    }
}
