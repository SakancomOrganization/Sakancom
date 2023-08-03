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

public class TestRejectBuildingUpdates {
    private int buildingId;
    @Given("info status of building to be rejected {int} is set to be dirty")
    public void infoStatusOfBuildingToBeRejectedIsSetToBeDirty(Integer buildingId) throws BuildingNotFoundException {
        this.buildingId = buildingId;
        Sakancom.getBuildingById(buildingId).setInfoStatus(InfoStatus.DIRTY);
    }
    @When("admin reject the changes of the building")
    public void adminRejectTheChangesOfTheBuilding() throws BuildingNotFoundException {
        Admin.rejectBuildingUpdates(buildingId);
    }
    @Then("info status of the building must be rejected")
    public void infoStatusOfTheBuildingMustBeRejected() throws BuildingNotFoundException {
        assertEquals(InfoStatus.REJECTED, Sakancom.getBuildingById(buildingId).getInfoStatus());
    }
    @When("admin wants to reject the changes of building {int}")
    public void adminWantsToRejectTheChangesOfBuilding(Integer buildingId) {
        this.buildingId =buildingId;
    }
    @Then("building info status will not be rejected and building not found exception will be thrown")
    public void buildingInfoStatusWillNotBeRejectedAndBuildingNotFoundExceptionWillBeThrown() {
        assertThrows(BuildingNotFoundException.class, () -> Admin.rejectBuildingUpdates(buildingId));
    }
}
