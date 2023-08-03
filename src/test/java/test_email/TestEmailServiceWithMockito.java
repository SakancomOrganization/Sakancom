package test_email;

import email.EmailService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.mail.MessagingException;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

public class TestEmailServiceWithMockito {
     private EmailService mockEmailService;

     @Before
     public void setup() throws FileNotFoundException {
         mockEmailService = mock(EmailService.class);
     }

     @Test
     public void testSendMailWithValidEmailAddress() throws MessagingException {
         mockEmailService.sendEmail("mo.a.alawneh@gmail.com");
         Mockito.verify(mockEmailService, times(1)).sendEmail("mo.a.alawneh@gmail.com");
     }

     @Test
     public void testSendMailWithInvalidEmailAddress() throws MessagingException {
         when(mockEmailService.sendEmail("ff")).thenThrow(MessagingException.class);
         assertThrows(MessagingException.class, () -> mockEmailService.sendEmail("ff"));
     }
}
