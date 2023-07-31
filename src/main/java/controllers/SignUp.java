package controllers;

import enums.UserType;
import exceptions.AlreadyFoundElementException;
import exceptions.WeakPasswordException;
import helpers.PasswordChecker;
import models.*;

public class SignUp {
    private SignUp() {

    }

    public static void signUp(String username, String password, UserType userType, Name name, UserLocation userLocation, ContactInfo contactInfo) throws AlreadyFoundElementException, WeakPasswordException {
        if(!PasswordChecker.isStrongPassword(password))
            throw new WeakPasswordException();
        User newUser = new User(username, password, userType, name, userLocation, contactInfo);
        Sakancom.addUser(newUser);
    }
}
