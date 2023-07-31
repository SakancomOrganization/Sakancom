package controllers;

import models.Sakancom;

public class Logout {
    private Logout() {

    }

    public static void logout() {
        Sakancom.setCurrentUser(null);
    }
}
