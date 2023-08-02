package test_helpers;

import helpers.StringsComparator;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestStringComparator {
    @Test
    public void testCompare() {
        // empty input
        assertTrue(StringsComparator.compare("Mohammad",""));

        // starts with (including ignore case)
        assertTrue(StringsComparator.compare("Mohammad","Moh"));
        assertTrue(StringsComparator.compare("Mohammad","moH"));

        // ends with (including ignore case)
        assertTrue(StringsComparator.compare("Mohammad","ad"));
        assertTrue(StringsComparator.compare("Mohammad","AD"));

        // contains (including ignore case)
        assertTrue(StringsComparator.compare("Mohammad","amm"));
        assertTrue(StringsComparator.compare("Mohammad","AmM"));

        // equals (including ignore case)
        assertTrue(StringsComparator.compare("Mohammad","Mohammad"));
        assertTrue(StringsComparator.compare("Mohammad","moHammAd"));

        // negative test case
        assertFalse(StringsComparator.compare("Mohammad", "Nour"));
    }
}
