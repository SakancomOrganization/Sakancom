package test_controllers.admin;

import controllers.Admin;
import enums.InfoStatus;
import exceptions.BuildingNotFoundException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Sakancom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TestAcceptBuildingUpdates {
    private int buildingId;
    @Given("info status of building to be accepted {int} is set to be dirty")
    public void infoStatusOfBuildingIsSetToBeDirty(Integer buildingId) throws BuildingNotFoundException {
        this.buildingId = buildingId;
        Sakancom.getBuildingById(buildingId).setInfoStatus(InfoStatus.REJECTED);
    }
    @When("admin accept the changes of the building")
    public void adminAcceptTheChanges() throws BuildingNotFoundException {
        Admin.acceptBuildingUpdate(buildingId);
    }
    @Then("info status of the building must be accepted")
    public void infoStatusOfTheBuildingMustBeAccepted() throws BuildingNotFoundException {
        assertEquals(InfoStatus.ACCEPTED, Sakancom.getBuildingById(buildingId).getInfoStatus());
    }
    @When("admin wants to accept the changes of building {int}")
    public void adminWantsToAcceptTheChangesOfBuilding(Integer buildingId) {
        this.buildingId = buildingId;
    }
    @Then("building info status will not be accepted and building not found exception will be thrown")
    public void buildingInfoStatusWillNotBeAcceptedAndBuildingNotFoundExceptionWillBeThrown() {
        assertThrows(BuildingNotFoundException.class, () -> Admin.acceptBuildingUpdate(buildingId));
    }
}
