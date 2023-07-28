package controllers;

import email.EmailService;
import models.Sakancom;
import models.User;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;
import java.util.List;

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
    public static boolean forgetPassword(String username) throws MessagingException, FileNotFoundException {
        List<User> users  = Sakancom.searchAboutUsers(username, null, null, "", "", "");
        if(users.isEmpty()) // invalid username
            return false;
        String newPassword = EmailService.sendEmail(users.get(0).getContactInfo().getEmail());
        users.get(0).setPassword(newPassword);
        return true;
    }
}
