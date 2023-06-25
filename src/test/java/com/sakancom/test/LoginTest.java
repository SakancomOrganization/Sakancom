package com.sakancom.test;

import com.sakancom.source.Login;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class LoginTest {

    private final Login login;
    public LoginTest(Login login) {
        this.login = login;
    }

    @When("username is {string}")
    public void usernameIs(String username) {
        login.setUsername(username);
    }
    @When("password is {string}")
    public void passwordIs(String password) {
        login.setPassword(password);
    }
    @When("type is {string}")
    public void typeIs(String type) {
        login.setType(type);
    }
    @Then("the user will log in successfully")
    public void theUserWillLogInSuccessfully() {
        assertTrue(login.canLogin());
    }
    @Then("the user will not log in successfully")
    public void theUserWillNotLogInSuccessfully() {
        assertFalse(login.canLogin());
    }
}
