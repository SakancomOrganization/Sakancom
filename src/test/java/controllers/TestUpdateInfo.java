package controllers;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Sakancom;
import models.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TestUpdateInfo {
    private String username;
    private String field;
    private String value;
    @When("username of who want to update is {string}")
    public void usernameOfWhoWantToUpdateIs(String username) {
        this.username = username;
    }
    @When("field is {string}")
    public void fieldIs(String field) {
        this.field = field;
    }
    @When("value is {string}")
    public void valueIs(String value) {
        this.value = value;
    }
    @Then("the information will be updated successfully")
    public void theInformationWillBeUpdatedSuccessfully() throws ParseException {
        UserGeneralOperations.updateInfo(username, field, value);
        User user = Sakancom.getUserByUsername(username);
        assert user != null;
        if(field.equalsIgnoreCase("firstName")) {
            assertEquals(value, user.getName().getFirstName());
        } else if(field.equalsIgnoreCase("secondName")) {
            assertEquals(value, user.getName().getMiddleName());
        } else if(field.equalsIgnoreCase("lastName")) {
            assertEquals(value, user.getName().getLastName());
        } else if(field.equalsIgnoreCase("city")) {
            assertEquals(value, user.getUserLocation().getCity());
        } else if(field.equalsIgnoreCase("street")) {
            assertEquals(value, user.getUserLocation().getStreet());
        } else if(field.equalsIgnoreCase("building")) {
            assertEquals(value, user.getUserLocation().getBuilding());
        } else if(field.equalsIgnoreCase("floor")) {
            assertEquals(Integer.parseInt(value), user.getUserLocation().getFloorNum());
        } else if(field.equalsIgnoreCase("email")) {
            assertEquals(value, user.getContactInfo().getEmail());
        } else if(field.equalsIgnoreCase("phoneNumber")) {
            assertEquals(value, user.getContactInfo().getPhoneNumber());
        } else if(field.equalsIgnoreCase("birthDate")) {
            assertEquals(new SimpleDateFormat("dd/MM/yyyy").parse(value), user.getContactInfo().getBirthdate());
        } else if(field.equalsIgnoreCase("major")) {
            assertEquals(value, user.getContactInfo().getMajor());
        }
    }
    @Then("the information will not be updated due to invalid username")
    public void theInformationWillNotBeUpdatedDueToInvalidUsername() {
        assertThrows(NullPointerException.class, () -> {
            UserGeneralOperations.updateInfo(username, field, value);
        });
    }
    @Then("the information will not be updated and number format exception will be thrown")
    public void theInformationWillNotBeUpdatedAndNumberFormatExceptionWillBeThrown() {
        assertThrows(NumberFormatException.class, () -> {
            UserGeneralOperations.updateInfo(username, field, value);
        });
    }
    @Then("the information will not be updated and date parse exception will be thrown")
    public void theInformationWillNotBeUpdatedAndDateParseExceptionWillBeThrown() {
        assertThrows(ParseException.class, () -> {
            UserGeneralOperations.updateInfo(username, field, value);
        });
    }
}
