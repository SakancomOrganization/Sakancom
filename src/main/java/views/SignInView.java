package views;

import controllers.Login;
import email.EmailService;
import exceptions.UserNotFoundException;
import models.Sakancom;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;
import java.util.logging.Logger;

public class SignInView {
    private static final Logger logger = Logger.getLogger(SignInView.class.getName());
    private SignInView() {

    }

    public static void signInView() {
        String username = CustomizedScanners.scanNonEmptyString("username");
        String password = CustomizedScanners.scanNonEmptyString("password");
        try {
            if(Login.login(username, password)) {
                String welcomeMsg = "Hello, " + Sakancom.getCurrentUser().getName().getFirstName();
                logger.info(welcomeMsg);
            } else {
                logger.warning("Invalid Password!");
            }
        } catch (UserNotFoundException e) {
            logger.warning("Invalid Username!");
        }
    }

    public static void forgetPasswordView() throws FileNotFoundException {
        String username = CustomizedScanners.scanNonEmptyString("username");
        try {
            Login.forgetPassword(new EmailService(), username);
            logger.info("We sent a message contains the new password on your email!");
        } catch (UserNotFoundException e) {
            logger.warning("Invalid Username!");
        } catch (MessagingException e) {
            logger.warning("Invalid email address!");
        }
    }
}
