package test_controllers.authentication;

import controllers.Login;
import enums.UserType;
import exceptions.UserNotFoundException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Sakancom;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGetCurrentUser {
    @When("current username is {string}")
    public void currentUsernameIs(String username) throws UserNotFoundException {
        Sakancom.setCurrentUser(Sakancom.getUserByUsername(username));
    }
    @Then("current user type is {string}")
    public void currentUserTypeIs(String userTypeAsString) {
        if(userTypeAsString.equalsIgnoreCase("admin")) {
            assertEquals(UserType.ADMIN, Login.getCurrentUserType());
        } else if(userTypeAsString.equalsIgnoreCase("owner")) {
            assertEquals(UserType.OWNER, Login.getCurrentUserType());
        } else if(userTypeAsString.equalsIgnoreCase("tenant")) {
            assertEquals(UserType.TENANT, Login.getCurrentUserType());
        }
    }
}
