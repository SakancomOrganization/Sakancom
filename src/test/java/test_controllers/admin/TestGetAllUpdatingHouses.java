package test_controllers.admin;

import controllers.Admin;
import enums.InfoStatus;
import exceptions.BuildingNotFoundException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Building;
import models.Sakancom;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestGetAllUpdatingHouses {
    private List<Building> updatedHouses;
    @When("building {int} info status is set to be dirty")
    public void buildingInfoStatusIsSetToBeDirty(Integer buildingId) throws BuildingNotFoundException {
        Sakancom.getBuildingById(buildingId).setInfoStatus(InfoStatus.DIRTY);
    }
    @When("building {int} info status is set to be accepted")
    public void buildingInfoStatusIsSetToBeAccepted(Integer buildingId) throws BuildingNotFoundException {
        Sakancom.getBuildingById(buildingId).setInfoStatus(InfoStatus.ACCEPTED);
    }
    @When("the current admin try to get all updating buildings")
    public void theCurrentAdminTryToGetAllUpdatingBuildings() {
        updatedHouses = Admin.getAllUpdatedBuildings();
    }
    @Then("the result size of the updating houses must be {int}")
    public void theResultSizeOfTheUpdatingHousesMustBe(Integer expectedSize) {
        Integer actualSize = updatedHouses.size();
        assertEquals(expectedSize, actualSize);
    }
}
