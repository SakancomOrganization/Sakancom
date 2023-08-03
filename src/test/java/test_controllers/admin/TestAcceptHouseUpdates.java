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

public class TestAcceptHouseUpdates {
    private int buildingId;
    private int houseId;
    @Given("info status of house {int} in building {int} to be accepted is set to be dirty")
    public void infoStatusOfHouseInBuildingToBeAcceptedIsSetToBeDirty(Integer houseId, Integer buildingId) throws BuildingNotFoundException, HouseNotFoundException {
        this.buildingId = buildingId;
        this.houseId = houseId;
        Sakancom.getBuildingById(buildingId).getHouseById(houseId).setInfoStatus(InfoStatus.DIRTY);
    }
    @When("admin accept the changes of the houses")
    public void adminAcceptTheChangesOfTheHouses() throws BuildingNotFoundException, HouseNotFoundException {
        Admin.acceptHouseUpdate(buildingId, houseId);
    }
    @Then("info status of the house must be accepted")
    public void infoStatusOfTheHouseMustBeAccepted() throws BuildingNotFoundException, HouseNotFoundException {
        assertEquals(InfoStatus.ACCEPTED, Sakancom.getBuildingById(buildingId).getHouseById(houseId).getInfoStatus());
    }
    @When("building id is {int} and house id is {int} to be accepted")
    public void buildingIdIsAndHouseIdIs(Integer buildingId, Integer houseId) {
        this.buildingId = buildingId;
        this.houseId = houseId;
    }
    @Then("info status of the house will not be accepted a building not found exception will be thrown")
    public void infoStatusOfTheHouseWillNotBeAcceptedABuildingNotFoundExceptionWillBeThrown() {
        assertThrows(BuildingNotFoundException.class, () -> Admin.acceptHouseUpdate(buildingId, houseId));
    }
    @Then("info status of the house will not be accepted a house not found exception will be thrown")
    public void infoStatusOfTheHouseWillNotBeAcceptedAHouseNotFoundExceptionWillBeThrown() {
        assertThrows(HouseNotFoundException.class, () -> Admin.acceptHouseUpdate(buildingId, houseId));
    }
}
