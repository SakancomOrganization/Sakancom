package controllers;

import email.EmailService;
import exceptions.UserNotFoundException;
import models.Sakancom;
import models.User;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;

public class Login {
    private Login () {

    }
    public static boolean login(String username, String password) throws UserNotFoundException {
        User user = Sakancom.getUserByUsername(username);
        if(user.getPassword().equals(password)) {
            Sakancom.setCurrentUser(user);
            return true;
        }
        return false;
    }
    public static void forgetPassword(String username) throws MessagingException, FileNotFoundException, UserNotFoundException {
        User user = Sakancom.getUserByUsername(username);
        String newPassword = EmailService.sendEmail(user.getContactInfo().getEmail());
        user.setPassword(newPassword);
    }
}
