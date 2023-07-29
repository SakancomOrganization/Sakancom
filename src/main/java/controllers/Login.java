package controllers;

import email.EmailService;
import models.Sakancom;
import models.User;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;

public class Login {
    private Login () {

    }
    public static boolean login(String username, String password) {
        for(User user : Sakancom.getUsers()) {
            if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
                User loggedInUser = new User(username, password, null, null, null , null);
                Sakancom.setCurrentUser(loggedInUser);
                return true;
            }
        }
        return false;
    }
    public static void forgetPassword(String username) throws MessagingException, FileNotFoundException, NullPointerException {
        User user = Sakancom.getUserByUsername(username);
        if (user == null)
            throw new NullPointerException();
        String newPassword = EmailService.sendEmail(user.getContactInfo().getEmail());
        user.setPassword(newPassword);
    }
}
