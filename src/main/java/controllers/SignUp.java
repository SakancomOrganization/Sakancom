package controllers;

import enums.UserType;
import models.*;

public class SignUp {
    private SignUp() {

    }
    public static boolean signUp(String username, String password, UserType userType, Name name, UserLocation userLocation, ContactInfo contactInfo) {
        if(Sakancom.searchAboutUsers(username, null, null, "", "", "").isEmpty()) {
            User newUser = new User(username, password, userType, name, userLocation, contactInfo);
            Sakancom.addUser(newUser);
            return true;
        }
        return false;
    }
}
