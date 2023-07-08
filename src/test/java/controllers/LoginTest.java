package controllers;

import io.cucumber.java.Before;
import enums.UserType;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static junit.framework.TestCase.assertFalse;

public class LoginTest {

    private Login login;
    @Before
    public void setup() {
        login = new Login(null);
    }
    @When("username is {string}")
    public void usernameIs(String username) {
        login.getUser().setUsername(username);
    }
    @When("password is {string}")
    public void passwordIs(String password) {
        login.getUser().setPassword(password);
    }
    @When("type is {string}")
    public void typeIs(String type) {
        if(type.equalsIgnoreCase("ADMIN"))
            login.getUser().setUserType(UserType.ADMIN);
        else if (type.equalsIgnoreCase("OWNER")) {
            login.getUser().setUserType(UserType.OWNER);
        }
        else if (type.equalsIgnoreCase("TENANT")) {
            login.getUser().setUserType(UserType.TENANT);
        }
    }
    @Then("the user will log in successfully")
    public void theUserWillLogInSuccessfully() {
        assertFalse(login.canLogin());
    }
    @Then("the user will not log in successfully")
    public void theUserWillNotLogInSuccessfully() {
        assertFalse(login.canLogin());
    }
}
