package test_email;

import email.EmailService;
import org.junit.Before;
import org.junit.Test;

import javax.mail.MessagingException;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TestEmailServiceWithMockito {
     private EmailService mockEmailService;
     private EmailService emailService;

     @Before
     public void setup() throws FileNotFoundException {
         mockEmailService = mock(EmailService.class);
         emailService = new EmailService();
     }

     @Test
     public void testReturnBody() {
         assertEquals("", emailService.getNewPassword());
     }

     @Test
     public void testSendEmailWithValidEmailAddress() throws MessagingException, IOException {
         // when
         mockEmailService.sendEmail("mo.a.alawneh@gmail.com");
         // then
         verify(mockEmailService, times(1)).sendEmail("mo.a.alawneh@gmail.com");
     }

     @Test
     public void testSendMailWithInvalidEmailAddress() {
         assertThrows(MessagingException.class, () -> emailService.sendEmail("ff"));
     }
}
