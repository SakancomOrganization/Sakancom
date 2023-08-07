package test_enums;

import enums.Colors;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestColors {
    @Test
    void testColors() {
        assertEquals("\u001B[33m", Colors.YELLOW.getUniCodeValue());
        assertEquals("\u001B[32m", Colors.GREEN.getUniCodeValue());
        assertEquals("\u001B[0m", Colors.RESET.getUniCodeValue());
        assertEquals("\u001B[47m", Colors.BACKGROUND_WHITE.getUniCodeValue());
    }
}
