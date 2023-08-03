package test_email;

import email.EmailService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.mail.MessagingException;

import java.io.FileNotFoundException;

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
     public void testReturnEmailValue() throws MessagingException {
         when(mockEmailService.sendEmail("mo.a.alawneh@gmail.com")).thenReturn("1234");
         assertEquals("1234", mockEmailService.sendEmail("mo.a.alawneh@gmail.com"));
     }

     @Test
     public void testSendEmailWithValidEmailAddress() throws MessagingException {
         mockEmailService.sendEmail("mo.a.alawneh@gmail.com");
         verify(mockEmailService, times(1)).sendEmail("mo.a.alawneh@gmail.com");
     }

     @Test
     public void testSendMailWithInvalidEmailAddress() {
         assertThrows(MessagingException.class, () -> emailService.sendEmail("ff"));
     }
}
