package controllers;

import enums.UserType;
import exceptions.AlreadyFoundElementException;
import models.*;

public class SignUp {
    private SignUp() {

    }
    public static boolean signUp(String username, String password, UserType userType, Name name, UserLocation userLocation, ContactInfo contactInfo) throws AlreadyFoundElementException {
        if(Sakancom.getUserByUsername(username) == null) {
            User newUser = new User(username, password, userType, name, userLocation, contactInfo);
            Sakancom.addUser(newUser);
            return true;
        }
        return false;
    }
}
