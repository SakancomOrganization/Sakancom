package test_controllers.owner;

import controllers.Owner;
import enums.InfoStatus;
import exceptions.BuildingNotFoundException;
import exceptions.UserNotFoundException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Building;
import models.Sakancom;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestListRejectedBuildings {
    private List<Building> rejectedBuildings;
    @Given("Admin change building with id {int} info status to rejected")
    public void adminChangeBuildingWithIdInfoStatusToRejected(Integer buildingId) throws BuildingNotFoundException {
        Sakancom.getBuildingById(buildingId).setInfoStatus(InfoStatus.REJECTED);
    }
    @Given("Admin change building with id {int} info status to accepted")
    public void adminChangeBuildingWithIdInfoStatusToAccepted(Integer buildingId) throws BuildingNotFoundException {
        Sakancom.getBuildingById(buildingId).setInfoStatus(InfoStatus.ACCEPTED);
    }
    @When("username of owner who wants to list the rejected houses is {string}")
    public void usernameOfOwnerWhoWantsToListTheRejectedHousesIs(String username) throws UserNotFoundException {
        Sakancom.setCurrentUser(Sakancom.getUserByUsername(username));
    }
    @When("owner wants to list all the rejected buildings")
    public void ownerWantsToListAllTheRejectedBuildings() {
        rejectedBuildings = Owner.listAllRejectedBuildings();
    }
    @Then("result size of the rejected buildings must be {int}")
    public void resultSizeOfTheRejectedBuildingsMustBe(Integer expectedSize) {
        Integer actualSize = rejectedBuildings.size();
        assertEquals(expectedSize, actualSize);
    }

}
