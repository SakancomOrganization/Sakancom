package email;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.Properties;

public class EmailService {
    private static final SecureRandom random = new SecureRandom(); // don't use the Random
    private static final String SUBJECT = "Verification Code";
    private final String from;
    private final String password;
    private String body;

    public EmailService() throws FileNotFoundException {
        from = YmlHandler.getValue("fromEmail");
        password = YmlHandler.getValue("password");
        body = "";
    }

    public String getBody() {
        return body;
    }

    private void setBody() throws IOException {
        // read the html
        body = Files.readString(Paths.get("src/main/resources/email-body.html"));
        // replace the holder
        body = body.replace("{{dynamic_text_placeholder}}", generateRandomString());
    }

    private Properties defineProperties() {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.ssl.checkserveridentity", "true");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        return prop;
    }

    private Session createSession() {
        return Session.getInstance(defineProperties(), new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });
    }

    public void sendEmail(String to) throws MessagingException, IOException {
        Message message = new MimeMessage(createSession());
        // set the src and dest
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        // set the subject
        message.setSubject(SUBJECT);

        // set the body
        setBody();

        // set the content (Multi part body consists of multi bodies)
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(body, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
        message.setContent(multipart);

        Transport.send(message);
    }

    private static String generateRandomString() {
        StringBuilder randomStr = new StringBuilder();
        for(int i = 0; i < 4; i++)
            randomStr.append(generateRandomDigit());
        return randomStr.toString();
    }

    private static int generateRandomDigit() {
        return random.nextInt(10);
    }

    public static void main(String[] args) throws IOException, MessagingException {
        EmailService emailService = new EmailService();
        emailService.sendEmail("mo.a.alawneh@gmail.com");
    }
}
