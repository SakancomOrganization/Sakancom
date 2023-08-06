package test_controllers.authentication;

import controllers.Login;
import exceptions.InvalidEmailFormatException;
import exceptions.UserNotFoundException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Sakancom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestUpdateEmail {
    private String username;
    private String newEmail;
    @When("username of user who wants to update the email is {string}")
    public void usernameOfUserWhoWantsToUpdateTheEmailIs(String username) {
        this.username = username;
    }
    @When("the new email is {string}")
    public void theNewEmailIs(String newEmail) {
        this.newEmail = newEmail;
    }
    @Then("the email will be updated successfully")
    public void theEmailWillBeUpdatedSuccessfully() throws UserNotFoundException, InvalidEmailFormatException {
        Login.updateEmail(username, newEmail);
        assertEquals(newEmail, Sakancom.getUserByUsername(username).getContactInfo().getEmail());
    }
    @Then("the email will not be updated and a user not found exception will be thrown")
    public void theEmailWillNotBeUpdatedAndAUserNotFoundExceptionWillBeThrown() {
        assertThrows(UserNotFoundException.class, () -> Login.updateEmail(username, newEmail));
    }
    @Then("the email will not be updated and an invalid email format exception will be thrown")
    public void theEmailWillNotBeUpdatedAndAnInvalidEmailFormatExceptionWillBeThrown() {
        assertThrows(InvalidEmailFormatException.class, () -> Login.updateEmail(username, newEmail));
    }
}
