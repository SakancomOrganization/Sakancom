package tests;

import controllers.Login;
import objects.Type;
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
        login.getUser().setUsername(username);
    }
    @When("password is {string}")
    public void passwordIs(String password) {
        login.getUser().setPassword(password);
    }
    @When("type is {string}")
    public void typeIs(String type) {
        if(type.equalsIgnoreCase("ADMIN"))
            login.getUser().setType(Type.ADMIN);
        else if (type.equalsIgnoreCase("OWNER")) {
            login.getUser().setType(Type.OWNER);
        }
        else if (type.equalsIgnoreCase("TENANT")) {
            login.getUser().setType(Type.TENANT);
        }
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
