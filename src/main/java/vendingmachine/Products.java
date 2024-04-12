package vendingmachine;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Products {
    private static final Pattern INFO_PATTERN = Pattern.compile("^\\[.*?];\\[.*?];?$");

    private final List<Product> value;

    public Products(final List<Product> value) {
        validate(value);
        this.value = value;
    }

    private void validate(final List<Product> value) {
        if (value.stream()
                 .map(Product::getName)
                 .distinct()
                 .count() != value.size()) {
            throw new IllegalArgumentException("중복된 제품명이 있습니다");
        }
    }

    public static Products ofInfo(final String info) {
        final Matcher matcher = INFO_PATTERN.matcher(info);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(
                    String.format("%s는 부적절한 입력 방식입니다.(EX:[콜라,1500,20];[사이다,1000,10])", info));
        }
        return new Products(parseInfo(info).stream()
                                           .map(Product::ofInfo)
                                           .toList());
    }

    private static List<String> parseInfo(String info) {
        return Arrays.stream(info.split(";"))
                     .map(s -> s.substring(1, s.length() - 1))
                     .toList();
    }

}
