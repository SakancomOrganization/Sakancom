package test_controllers.admin;

import controllers.Admin;
import enums.InfoStatus;
import exceptions.BuildingNotFoundException;
import exceptions.HouseNotFoundException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Sakancom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TestRejectHouseUpdates {
    private int buildingId;
    private int houseId;
    @Given("info status of house {int} in building {int} to be rejected is set to be dirty")
    public void infoStatusOfHouseInBuildingToBeRejectedIsSetToBeDirty(Integer houseId, Integer buildingId) throws BuildingNotFoundException, HouseNotFoundException {
        this.buildingId = buildingId;
        this.houseId = houseId;
        Sakancom.getBuildingById(buildingId).getHouseById(houseId).setInfoStatus(InfoStatus.DIRTY);
    }
    @When("admin reject the changes of the houses")
    public void adminRejectTheChangesOfTheHouses() throws BuildingNotFoundException, HouseNotFoundException {
        Admin.rejectHouseUpdate(buildingId, houseId);
    }
    @Then("info status of the house must be rejected")
    public void infoStatusOfTheHouseMustBeRejected() throws BuildingNotFoundException, HouseNotFoundException {
        assertEquals(InfoStatus.REJECTED, Sakancom.getBuildingById(buildingId).getHouseById(houseId).getInfoStatus());
    }
    @When("building id is {int} and house id is {int} to be rejected")
    public void buildingIdIsAndHouseIdIsToBeRejected(Integer buildingId, Integer houseId) {
        this.buildingId = buildingId;
        this.houseId = houseId;
    }
    @Then("info status of the house will not be rejected a building not found exception will be thrown")
    public void infoStatusOfTheHouseWillNotBeRejectedABuildingNotFoundExceptionWillBeThrown() {
        assertThrows(BuildingNotFoundException.class, () -> Admin.rejectHouseUpdate(buildingId, houseId));
    }
    @Then("info status of the house will not be rejected a house not found exception will be thrown")
    public void infoStatusOfTheHouseWillNotBeRejectedAHouseNotFoundExceptionWillBeThrown() {
        assertThrows(HouseNotFoundException.class, () -> Admin.rejectHouseUpdate(buildingId, houseId));
    }
}
