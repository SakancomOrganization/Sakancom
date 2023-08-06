package test_controllers.authentication;

import controllers.Login;
import email.EmailService;
import exceptions.AlreadyFoundElementException;
import exceptions.InvalidEmailFormatException;
import exceptions.UnacceptableValueException;
import exceptions.UserNotFoundException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Sakancom;
import org.mockito.Mockito;

import javax.mail.MessagingException;
import java.text.ParseException;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

public class TestLogin {
    private String username;
    private String password;
    @Given("Database is already filled")
    public static void databaseIsAlreadyFilled() throws ParseException, UnacceptableValueException, AlreadyFoundElementException, InvalidEmailFormatException {
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
        assertThrows(UserNotFoundException.class, () -> Login.login(username, password));
    }
    @Then("the user will not log in successfully due to invalid password")
    public void theUserWillNotLogInSuccessfullyDueToInvalidPassword() throws UserNotFoundException {
        assertFalse(Login.login(username, password));
    }
}
