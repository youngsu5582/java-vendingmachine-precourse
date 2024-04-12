package vendingmachine;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NameTest {
    @Test
    void create_name_with_string() {
        final var value = "사이다";

        final var result = new Name(value);
        assertThat(result).isEqualTo(Name.from("사이다"));
    }
}
