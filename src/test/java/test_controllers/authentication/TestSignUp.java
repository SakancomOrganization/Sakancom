package test_controllers.authentication;

import controllers.SignUp;
import enums.UserType;
import exceptions.AlreadyFoundElementException;
import exceptions.InvalidEmailFormatException;
import exceptions.UnacceptableValueException;
import exceptions.WeakPasswordException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.ContactInfo;
import models.Name;
import models.Sakancom;
import models.UserLocation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TestSignUp {
    private String username;
    private String password;
    private UserType userType;
    private Name name;
    private UserLocation userLocation;
    private ContactInfo contactInfo;

    @When("newUsername is {string}")
    public void newUsernameIs(String newUsername) {
        this.username = newUsername;
    }
    @When("newPassword is {string}")
    public void newPasswordIs(String newPassword) {
        this.password = newPassword;
    }
    @When("usertype is {string}")
    public void usertypeIs(String userType) {
        if(userType.equalsIgnoreCase("admin"))
            this.userType = UserType.ADMIN;
        else if(userType.equalsIgnoreCase("owner"))
            this.userType = UserType.OWNER;
        else if(userType.equalsIgnoreCase("tenant"))
            this.userType = UserType.TENANT;
    }
    @When("name is {string} {string} {string}")
    public void nameIs(String firstName, String secondName, String lastName) {
        this.name = new Name(firstName, secondName, lastName);
    }
    @When("location is {string} {string} {string} {int}")
    public void locationIs(String city, String street, String building, Integer floor) throws UnacceptableValueException {
        this.userLocation = new UserLocation(city, street, building, floor);
    }
    @When("contactInfo are {string} {string} {string} {string}")
    public void contactInfoAre(String email, String phoneNumber, String birthDate, String major) throws ParseException, InvalidEmailFormatException {
        Date birthDateObject = new SimpleDateFormat("dd/MM/yyyy").parse(birthDate);
        this.contactInfo = new ContactInfo(email, phoneNumber, birthDateObject, major);
    }
    @Then("the user will sign up successfully")
    public void theUserWillSignUpSuccessfully() {
        assertDoesNotThrow(() -> {
            SignUp.signUp(username, password, userType, name, userLocation, contactInfo);
            Sakancom.getUserByUsername(username);
        });
    }
    @Then("the user will not sign up due to already found username")
    public void theUserWillNotSignUp() {
        assertThrows(AlreadyFoundElementException.class, () -> SignUp.signUp(username, password, userType, name, userLocation, contactInfo));
    }
    @Then("the user will not sign up due weak password and weak password exception will be thrown")
    public void theUserWillNotSignUpDueWeakPasswordAndWeakPasswordExceptionWillBeThrown() {
        assertThrows(WeakPasswordException.class, () -> SignUp.signUp(username, password, userType, name, userLocation, contactInfo));
    }
}
