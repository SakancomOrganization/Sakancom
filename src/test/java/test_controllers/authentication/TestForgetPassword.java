package test_controllers.authentication;

import controllers.Login;
import email.EmailService;
import exceptions.UserNotFoundException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Sakancom;

import javax.mail.MessagingException;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;

public class TestForgetPassword {
    private String username;
    @When("username who forgets the password is {string}")
    public void usernameIs(String username) {
        this.username = username;
    }
    @Then("the user will receive a new password on the email")
    public void theUserWillReceiveANewPasswordOnTheEmail() throws MessagingException, UserNotFoundException {
        String previousPassword = Sakancom.getUserByUsername(username).getPassword();
        Login.forgetPassword(mock(EmailService.class), username);
        String updatedPassword = Sakancom.getUserByUsername(username).getPassword();
        assertNotEquals(previousPassword, updatedPassword);
    }
    @Then("the user will not receive a new password on the email and a user not found exception will be thrown")
    public void theUserWillNotReceiveANewPasswordOnTheEmail() {
        assertThrows(UserNotFoundException.class, () -> Login.forgetPassword(mock(EmailService.class),username));
    }
}
