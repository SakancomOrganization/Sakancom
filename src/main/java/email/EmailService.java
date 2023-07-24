package email;

import io.cucumber.java.it.Ma;
import org.yaml.snakeyaml.Yaml;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.Random;

public class EmailService {
    private static final String SUBJECT = "Verification Code";
    private static final Random random = new Random(10);

    public void sendEmail(String to) throws MessagingException, FileNotFoundException {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.ssl.checkserveridentity", "true");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        String from = getValue("fromEmail");
        String password = getValue("password");

        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(SUBJECT);

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        String body = generateRandomString();
        mimeBodyPart.setContent(body, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
        message.setContent(multipart);

        Transport.send(message);
    }

    private String generateRandomString() {
        StringBuilder randomStr = new StringBuilder();
        for(int i = 0; i < 4; i++)
            randomStr.append(generateRandomDigit());
        return randomStr.toString();
    }

    private int generateRandomDigit() {
        return random.nextInt(10);
    }

    private Map<String, Object> init() throws FileNotFoundException {
        Map<String, Object> data;
        InputStream inputStream = new FileInputStream("config.yml");
        Yaml yml = new Yaml();
        data = yml.load(inputStream);
        return data;
    }

    private String getValue(String key) throws FileNotFoundException {
        return init().get(key).toString();
    }
}
