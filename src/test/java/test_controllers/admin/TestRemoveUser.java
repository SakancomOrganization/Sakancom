package test_controllers.admin;

import controllers.Admin;
import exceptions.AdminCannotBeRemovedException;
import exceptions.UserNotFoundException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Sakancom;

import static org.junit.Assert.assertThrows;

public class TestRemoveUser {
    private String username;
    @When("username of the user to be removed is {string}")
    public void usernameOfTheUserToBeRemovedIs(String username) {
        this.username = username;
    }
    @Then("this user will be removed")
    public void thisUserWillBeRemoved() throws UserNotFoundException, AdminCannotBeRemovedException {
        Admin.removeUser(username);
        // the assertThrows here just to check that the user is removed and an exception will be thrown
        // if try to get him
        assertThrows(UserNotFoundException.class, () -> Sakancom.getUserByUsername(username));
    }
    @Then("nothing will be removed and a user not found exception will be thrown")
    public void nothingWillBeRemovedAndAUserNotFoundExceptionWillBeThrown() {
        assertThrows(UserNotFoundException.class, () -> Admin.removeUser(username));

    }
    @Then("nothing will be removed and an admin cannot be removed exception will be thrown")
    public void nothingWillBeRemovedAndAnAdminCannotBeRemovedExceptionWillBeThrown() {
        assertThrows(AdminCannotBeRemovedException.class, () -> Admin.removeUser(username));
    }
}
