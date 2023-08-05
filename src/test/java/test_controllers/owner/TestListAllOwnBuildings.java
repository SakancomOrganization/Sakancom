package test_controllers.owner;

import controllers.Owner;
import exceptions.AlreadyFoundElementException;
import exceptions.InvalidEmailFormatException;
import exceptions.UnacceptableValueException;
import exceptions.UserNotFoundException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Building;
import models.Sakancom;

import java.text.ParseException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestListAllOwnBuildings {
    private List<Building> ownBuildings;
    @Given("Database is already filled")
    public void databaseIsAlreadyFilled() throws AlreadyFoundElementException, UnacceptableValueException, ParseException, InvalidEmailFormatException {
        Sakancom.initSakancomWithData();
    }
    @When("the current owner who wants to list own buildings is {string}")
    public void theOwnerUsernameIs(String username) throws UserNotFoundException {
        Sakancom.setCurrentUser(Sakancom.getUserByUsername(username));
    }
    @When("the user wants to list all own buildings")
    public void theUserWantsToListAllOwnBuildings() {
        ownBuildings = Owner.listAllOwnBuildings();
    }
    @Then("the result size of the buildings must be {int}")
    public void theResultSizeMustBe(Integer expectedSize) {
        Integer actualSize = ownBuildings.size();
        assertEquals(expectedSize, actualSize);
    }
}
