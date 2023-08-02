package test_controllers.owner;

import controllers.Owner;
import enums.InfoStatus;
import exceptions.BuildingNotFoundException;
import exceptions.HouseNotFoundException;
import exceptions.UserNotFoundException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.House;
import models.Sakancom;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestListAllRejectedHouses {
    private List<House> rejectedHouses;
    @Given("Admin change the info status of the house with id {int} in the building id {int} to be rejected")
    public void adminChangeTheInfoStatusOfTheHouseWithIdInTheBuildingIdToBeRejected(Integer buildingId, Integer houseId) throws BuildingNotFoundException, HouseNotFoundException {
        Sakancom.getBuildingById(buildingId).getHouseById(houseId).setInfoStatus(InfoStatus.REJECTED);
    }
    @Given("Admin change the info status of the house with id {int} in the building id {int} to be accepted")
    public void adminChangeTheInfoStatusOfTheHouseWithIdInTheBuildingIdToBeAccepted(Integer buildingId, Integer houseId) throws BuildingNotFoundException, HouseNotFoundException {
        Sakancom.getBuildingById(buildingId).getHouseById(houseId).setInfoStatus(InfoStatus.ACCEPTED);
    }
    @When("current owner who wants to list the rejected houses is {string}")
    public void currentOwnerWhoWantsToListTheRejectedHousesIs(String username) throws UserNotFoundException {
        Sakancom.setCurrentUser(Sakancom.getUserByUsername(username));
    }
    @When("owner list the rejected houses")
    public void ownerListTheRejectedHouses() {
        rejectedHouses = Owner.listAllRejectedHouses();
    }
    @Then("the result size of the rejected houses must be {int}")
    public void theResultSizeOfTheRejectedHousesMustBe(Integer expectedSize) {
        Integer actualSize = rejectedHouses.size();
        assertEquals(expectedSize, actualSize);
    }
}
