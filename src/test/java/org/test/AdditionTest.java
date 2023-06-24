package org.test;

import org.code.Addition;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AdditionTest {
    @Test
    void testAdd() {
        Addition addition = new Addition();
        assertEquals(0, addition.add());
        assertEquals(1, addition.add(1));
        assertEquals(9, addition.add(4, 5));
        assertEquals(12, addition.add(4, 5, 3));
        assertEquals(-2, addition.add(-2, 0));
        assertEquals(-4, addition.add(-2, -3, 1));
    }

}