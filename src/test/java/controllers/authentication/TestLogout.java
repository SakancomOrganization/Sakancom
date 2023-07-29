package controllers.authentication;

import controllers.Login;
import controllers.Logout;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Sakancom;

import static org.junit.Assert.assertNull;

public class TestLogout {
    @Given("a user is already logged in with {string} and {string}")
    public void aUserIsAlreadyLoggedInWithAnd(String username, String password) {
        Login.login(username, password);
    }
    @When("user logged out")
    public void userLoggedOut() {
        Logout.logout();
    }
    @Then("the current user will be null")
    public void theCurrentUserWillBeNull() {
        assertNull(Sakancom.getCurrentUser());
    }
}
