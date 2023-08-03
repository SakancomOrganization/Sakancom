package controllers;

import email.EmailService;
import exceptions.UserNotFoundException;
import models.Sakancom;
import models.User;

import javax.mail.MessagingException;

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
    public static void forgetPassword(EmailService emailService, String username) throws MessagingException, UserNotFoundException {
        User user = Sakancom.getUserByUsername(username);
        emailService.sendEmail(user.getContactInfo().getEmail());
        user.setPassword(emailService.getBody());
    }
}
