package email;

import org.junit.Before;
import org.junit.Test;

import javax.mail.MessagingException;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TestEmailService {
    private EmailService emailService;

    @Before
    public void setup() {
        emailService = new EmailService();
    }

    @Test
    public void testSendEMailWithValidEmailAddress() {
        assertDoesNotThrow(() -> {
            emailService.sendEmail("mo.a.alawneh@gmail.com","Test","Test Email Message");
        });
    }

    @Test
    public void testSendEMailWithInvalidEmailAddress() {
        assertThrows(MessagingException.class, () -> {
            emailService.sendEmail("ff","Test","Test Email Message");
        });
    }
}
