package test_controllers.admin;

import controllers.Admin;
import enums.UserType;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Name;

import static org.junit.Assert.assertEquals;

public class TestSearchAboutUsers {
    private String username;
    private UserType userType;
    private Name name;
    private String email;
    private String phoneNumber;
    private String major;
    @When("username is {string}")
    public void usernameIs(String username) {
        this.username = username;
    }
    @When("user type is {string}")
    public void userTypeIs(String userType) {
        if(userType.equalsIgnoreCase("Admin")) {
            this.userType = UserType.ADMIN;
        } else if(userType.equalsIgnoreCase("Owner")) {
            this.userType = UserType.OWNER;
        } else if(userType.equalsIgnoreCase("Tenant")) {
            this.userType = UserType.TENANT;
        } else {
            this.userType = null;
        }
    }
    @When("name is {string} {string} {string}")
    public void nameIs(String firstName, String middleName, String lastName) {
        this.name = new Name(firstName, middleName, lastName);
    }
    @When("email is {string}")
    public void emailIs(String email) {
        this.email = email;
    }
    @When("phoneNumber is {string}")
    public void phoneNumberIs(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    @When("major is {string}")
    public void majorIs(String major) {
        this.major = major;
    }
    @Then("the result users size must be {int}")
    public void theResultUsersSizeMustBe(Integer expectedSize) {
        Integer actualSize = Admin.searchAboutUsers(username, userType, name, email, phoneNumber, major).size();
        assertEquals(expectedSize, actualSize);
    }
}
