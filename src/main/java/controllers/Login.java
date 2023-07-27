package controllers;

import models.User;

public class Login {
    private final User user;

    public Login(User user) {
        this.user = user;
    }
    public User getUser() {
        return user;
    }

    public boolean canLogin() {
        return false;
    }
}
