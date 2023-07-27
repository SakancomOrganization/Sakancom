package controllers;

import models.Sakancom;

public class Logout {
    private Logout() {

    }
    public static void Logout() {
        Sakancom.setCurrentUser(null);
    }
}
