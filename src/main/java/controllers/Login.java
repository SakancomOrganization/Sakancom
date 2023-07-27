package controllers;

import models.Sakancom;
import models.User;

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
}
