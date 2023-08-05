package test_controllers.admin;

import controllers.Admin;
import exceptions.AlreadyFoundElementException;
import exceptions.InvalidEmailFormatException;
import exceptions.UnacceptableValueException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Sakancom;
import models.User;

import java.text.ParseException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestListAllUsers {
    private List<User> users;
    @Given("Database is already filled")
    public void databaseIsAlreadyFilled() throws AlreadyFoundElementException, UnacceptableValueException, ParseException, InvalidEmailFormatException {
        Sakancom.initSakancomWithData();
    }
    @When("the admin wants to list all users")
    public void theAdminWantsToListAllUsers() {
        users = Admin.listAllUsers();
    }
    @Then("the result size of the users must be {int}")
    public void theResultSizeOfTheUsersMustBe(Integer expectedSize) {
        Integer actualSize = users.size();
        assertEquals(expectedSize, actualSize);
    }
}
