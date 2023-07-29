package controllers.authentication;

import controllers.Login;
import exceptions.AlreadyFoundElementException;
import exceptions.UnacceptableValueException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Sakancom;

import java.text.ParseException;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TestLogin {
    private String username;
    private String password;
    @Given("Database is already filled")
    public static void databaseIsAlreadyFilled() throws ParseException, AlreadyFoundElementException, UnacceptableValueException {
        Sakancom.initSakancomWithData();
    }
    @When("username is {string}")
    public void usernameIs(String username) {
        this.username = username;
    }
    @When("password is {string}")
    public void passwordIs(String password) {
        this.password = password;
    }
    @Then("the user will log in successfully")
    public void theUserWillLogInSuccessfully() {
        assertTrue(Login.login(username, password));
        assertNotNull(Sakancom.getCurrentUser());
    }
    @Then("the user will not log in successfully")
    public void theUserWillNotLogInSuccessfully() {
        assertFalse(Login.login(username, password));
    }
    @Then("the user will receive a new password on the email")
    public void theUserWillReceiveANewPasswordOnTheEmail()  {
        assertDoesNotThrow(() -> {
            Login.forgetPassword(username);
        });
    }
    @Then("the user will not receive a new password on the email")
    public void theUserWillNotReceiveANewPasswordOnTheEmail() {
        assertThrows(NullPointerException.class, () -> {
            Login.forgetPassword(username);
        });
    }
}
