package test_controllers.owner;

import controllers.Owner;
import exceptions.BuildingNotFoundException;
import exceptions.UserNotFoundException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.House;
import models.Sakancom;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TestListAllHousesInOwnBuilding {
    private int buildingId;
    private List<House> ownHouses;
    @When("the current owner who wants to list houses in a building is {string}")
    public void theCurrentOwnerWhoWantsToListHousesInABuildingIs(String username) throws UserNotFoundException {
        Sakancom.setCurrentUser(Sakancom.getUserByUsername(username));
    }
    @When("building id to list its houses is {int}")
    public void buildingIdToListItsHousesIs(Integer buildingId) {
        this.buildingId = buildingId;
    }
    @When("the user list all houses in an own building")
    public void theUserListAllHousesInAnOwnBuilding() throws BuildingNotFoundException {
        ownHouses = Owner.listAllHousesInOwnBuilding(buildingId);
    }
    @Then("the result size of the houses is {int}")
    public void theResultSizeOfTheHousesIs(Integer expectedSize) {
        Integer actualSize = ownHouses.size();
        assertEquals(expectedSize, actualSize);
    }
    @Then("the list of houses in a building failed and building not found exception will be thrown")
    public void theListOfHousesInABuildingFailedAndBuildingNotFoundExceptionWillBeThrown() {
        assertThrows(BuildingNotFoundException.class, () -> Owner.listAllHousesInOwnBuilding(buildingId));
    }
}
