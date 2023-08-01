package test_controllers.user_general_operations;

import controllers.UserGeneralOperations;
import exceptions.AlreadyFoundElementException;
import exceptions.UnacceptableValueException;
import exceptions.UserNotFoundException;
import exceptions.WeakPasswordException;
import io.cucumber.java.en.Given;
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
    @Given("Database is already filled")
    public static void databaseIsAlreadyFilled() throws ParseException, AlreadyFoundElementException, UnacceptableValueException {
        Sakancom.initSakancomWithData();
    }
    @When("{string} is already logged in")
    public void isAlreadyLoggedIn(String username) throws UserNotFoundException {
        this.username = username;
        Sakancom.setCurrentUser(Sakancom.getUserByUsername(username));
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
    public void theInformationWillBeUpdatedSuccessfully() throws ParseException, UnacceptableValueException, UserNotFoundException, WeakPasswordException {
        UserGeneralOperations.updateInfo(field, value);
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
    @Then("the information will not be updated and weak password exception will be thrown")
    public void theInformationWillNotBeUpdatedAndWeakPasswordExceptionWillBeThrown() {
        assertThrows(WeakPasswordException.class, () -> UserGeneralOperations.updateInfo(field, value));
    }
    @Then("the information will not be updated and number format exception will be thrown")
    public void theInformationWillNotBeUpdatedAndNumberFormatExceptionWillBeThrown() {
        assertThrows(NumberFormatException.class, () -> UserGeneralOperations.updateInfo(field, value));
    }
    @Then("the information will not be updated and unacceptable value exception will be thrown")
    public void theInformationWillNotBeUpdatedAndUnacceptableValueExceptionWillBeThrown() {
        assertThrows(UnacceptableValueException.class, () -> UserGeneralOperations.updateInfo(field, value));
    }
    @Then("the information will not be updated and date parse exception will be thrown")
    public void theInformationWillNotBeUpdatedAndDateParseExceptionWillBeThrown() {
        assertThrows(ParseException.class, () -> UserGeneralOperations.updateInfo(field, value));
    }
}
