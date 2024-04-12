package vendingmachine;

public record Name(String value) {
    public static Name from(String value) {
        return new Name(value);
    }
}
