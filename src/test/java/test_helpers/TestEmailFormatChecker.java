package test_helpers;

import helpers.EmailFormatChecker;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestEmailFormatChecker {
    @Test
    public void testHasCorrectEmailFormat() {
        assertTrue(EmailFormatChecker.hasCorrectEmailFormat("mo.a.alawneh@gmail.com"));
        assertTrue(EmailFormatChecker.hasCorrectEmailFormat("s12028067@outlook.com"));
        assertTrue(EmailFormatChecker.hasCorrectEmailFormat("mohammad12002jaba@hotmail.com"));
        assertTrue(EmailFormatChecker.hasCorrectEmailFormat("test@yahoo.com"));
        assertTrue(EmailFormatChecker.hasCorrectEmailFormat("s12028067@najah.edu"));
        assertTrue(EmailFormatChecker.hasCorrectEmailFormat("test@stu.najah.edu"));
        assertFalse(EmailFormatChecker.hasCorrectEmailFormat("ff"));
        assertFalse(EmailFormatChecker.hasCorrectEmailFormat("ff@ff"));
        assertFalse(EmailFormatChecker.hasCorrectEmailFormat("f.f"));
        assertFalse(EmailFormatChecker.hasCorrectEmailFormat("test@stu.najah.com"));
        assertFalse(EmailFormatChecker.hasCorrectEmailFormat("test@najah.com"));
    }
}
