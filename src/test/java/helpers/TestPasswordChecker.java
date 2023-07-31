package helpers;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestPasswordChecker {
    @Test
    public void testIsStrongPassword() {
        assertFalse(PasswordChecker.isStrongPassword("")); // empty
        assertFalse(PasswordChecker.isStrongPassword("Pass123")); // length < 8
        assertFalse(PasswordChecker.isStrongPassword("Password123")); // missing special chars
        assertFalse(PasswordChecker.isStrongPassword("Password#@!")); // missing digits
        assertFalse(PasswordChecker.isStrongPassword("password#$%1")); // missing capital letters
        assertFalse(PasswordChecker.isStrongPassword("PASSWORD#@!$22")); // missing small letters
        assertTrue(PasswordChecker.isStrongPassword("Password1234#2002")); // strong password
    }
}
