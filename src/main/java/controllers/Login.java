package controllers;

import email.EmailService;
import enums.UserType;
import exceptions.InvalidEmailFormatException;
import exceptions.UserNotFoundException;
import models.Sakancom;
import models.User;

import javax.mail.MessagingException;
import java.io.IOException;

public class Login {
    private Login () {

    }

    /**
        @param username the username of the user who wants to log in
        @param password the password of the user who wants to log in
        @throws UserNotFoundException if the username is invalid
        @return can the user log in or not
     */
    public static boolean login(String username, String password) throws UserNotFoundException {
        User user = Sakancom.getUserByUsername(username);
        if(user.getPassword().equals(password)) {
            Sakancom.setCurrentUser(user);
            return true;
        }
        return false;
    }

    public static UserType getCurrentUserType() {
        return Sakancom.getCurrentUser().getUserType();
    }

    public static void forgetPassword(EmailService emailService, String username) throws MessagingException, UserNotFoundException, IOException {
        User user = Sakancom.getUserByUsername(username);
        emailService.sendEmail(user.getContactInfo().getEmail());
        user.setPassword(emailService.getNewPassword());
    }

    public static void updateEmail(String username, String email) throws UserNotFoundException, InvalidEmailFormatException {
        Sakancom.getUserByUsername(username).getContactInfo().setEmail(email);
    }
}
