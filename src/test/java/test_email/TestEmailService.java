package test_email;

import email.EmailService;
import org.junit.Test;

import javax.mail.MessagingException;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TestEmailService {

    @Test
    public void testSendEmailWithValidEmailAddress() {
        assertDoesNotThrow(() -> {
            EmailService.sendEmail("mo.a.alawneh@gmail.com");
        });
    }

    @Test
    public void testSendEmailWithInvalidEmailAddress() {
        assertThrows(MessagingException.class, () -> {
            EmailService.sendEmail("ff");
        });
    }
}
