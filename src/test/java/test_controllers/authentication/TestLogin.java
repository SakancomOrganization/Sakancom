package test_controllers.authentication;

import controllers.Login;
import exceptions.AlreadyFoundElementException;
import exceptions.UnacceptableValueException;
import exceptions.UserNotFoundException;
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
    public static void databaseIsAlreadyFilled() throws ParseException, UnacceptableValueException, AlreadyFoundElementException {
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
    public void theUserWillLogInSuccessfully() throws UserNotFoundException {
        assertTrue(Login.login(username, password));
        assertNotNull(Sakancom.getCurrentUser());
    }
    @Then("the user will not log in successfully and user not found exception will be thrown")
    public void theUserWillNotLogInSuccessfullyAndUserNotFoundExceptionWillBeThrown() {
        assertThrows(UserNotFoundException.class, () -> {
            Login.login(username, password);
        });
    }
    @Then("the user will not log in successfully due to invalid password")
    public void theUserWillNotLogInSuccessfullyDueToInvalidPassword() throws UserNotFoundException {
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
        assertThrows(UserNotFoundException.class, () -> {
            Login.forgetPassword(username);
        });
    }
}
