package vendingmachine;

public record Quantity(int value) {

    private static final int MINIMUM_VALUE = 0;

    public static Quantity from(int value) {
        validate(value);
        return new Quantity(value);
    }

    private static void validate(final int value) {
        if (value <= MINIMUM_VALUE) {
            throw new IllegalArgumentException(String.format("%d보다 커야합니다.", MINIMUM_VALUE));
        }
    }

    public Quantity minus(final int value) {
        if (this.value < value) {
            throw new IllegalStateException(String.format("현재 개수는 %d 입니다.", this.value));
        }
        return new Quantity(this.value - value);
    }


}
