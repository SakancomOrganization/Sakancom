package views;

import controllers.Login;
import email.EmailService;
import exceptions.InvalidEmailFormatException;
import exceptions.UserNotFoundException;
import models.Sakancom;
import scanners.CustomizedScanners;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.logging.Logger;

public class LoginView {
    private static final Logger logger = Logger.getLogger(LoginView.class.getName());
    public static final String USERNAME = "username";

    private LoginView() {

    }

    public static void loginView() {
        String username = CustomizedScanners.scanNonEmptyString(USERNAME);
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

    public static void forgetPasswordView() throws IOException {
        String username = CustomizedScanners.scanNonEmptyString(USERNAME);
        try {
            Login.forgetPassword(new EmailService(), username);
            logger.info("We sent a message contains the new password on your email!");
        } catch (UserNotFoundException e) {
            logger.warning("Invalid username!");
        } catch (MessagingException e) {
            logger.warning("Invalid email address!");
        }
    }

    public static void updateEmailView() {
        String username = CustomizedScanners.scanNonEmptyString(USERNAME);
        String newEmail = CustomizedScanners.scanNonEmptyString("email");
        try {
            Login.updateEmail(username, newEmail);
            logger.info("The email is updated successfully!");
        } catch (UserNotFoundException e) {
            logger.warning("Invalid username!");
        } catch (InvalidEmailFormatException e) {
            logger.warning("Invalid email address!");
        }
    }
}
