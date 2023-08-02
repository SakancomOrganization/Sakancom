package test_controllers.owner;

import controllers.Owner;
import exceptions.AlreadyFoundElementException;
import exceptions.UserNotFoundException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Building;
import models.Location;
import models.Sakancom;

import static org.junit.Assert.*;

public class TestAddBuilding {
    private String name;
    private Location location;
    @When("added building name is {string}")
    public void addedBuildingNameIs(String name) {
        this.name = name;
    }
    @When("added building owner has a username {string}")
    public void addedBuildingOwnerHasAUsername(String username) throws UserNotFoundException {
        Sakancom.setCurrentUser(Sakancom.getUserByUsername(username));
    }
    @When("added building location is {string} {string}")
    public void addedBuildingLocationIs(String city, String street) {
        location = new Location(city, street);
    }
    @Then("Building will be added successfully")
    public void buildingWillBeAddedSuccessfully() throws AlreadyFoundElementException {
        int sizeBeforeAddition = Sakancom.getBuildings().size();
        Owner.addBuilding(new Building(-1, name, Sakancom.getCurrentUser(), location));
        int sizeAfterAddition = Sakancom.getBuildings().size();
        assertEquals(sizeAfterAddition, sizeBeforeAddition + 1);
    }
    @Then("Building will not be added successfully and already found element exception will be thrown")
    public void buildingWillNotBeAddedSuccessfullyAndAlreadyFoundElementExceptionWillBeThrown() {
        assertThrows(AlreadyFoundElementException.class, () -> Owner.addBuilding(new Building(-1, name, Sakancom.getCurrentUser(), location)));
    }
}
