package vendingmachine;

public class Parser {
    private Parser() {}
    
    public static int toInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format("%s는 숫자가 아닙니다.", value), e);
        }
    }
}
