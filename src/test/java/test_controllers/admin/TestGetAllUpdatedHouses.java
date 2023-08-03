package test_controllers.admin;

import controllers.Admin;
import enums.InfoStatus;
import exceptions.BuildingNotFoundException;
import exceptions.HouseNotFoundException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.House;
import models.Sakancom;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestGetAllUpdatedHouses {
    private List<House> updatedHouses;
    @When("house {int} building {int} info status is set to be dirty")
    public void houseBuildingInfoStatusIsSetToBeDirty(Integer houseId, Integer buildingId) throws BuildingNotFoundException, HouseNotFoundException {
        Sakancom.getBuildingById(buildingId).getHouseById(houseId).setInfoStatus(InfoStatus.DIRTY);
    }
    @When("house {int} building {int} info status is set to be accepted")
    public void houseBuildingInfoStatusIsSetToBeAccepted(Integer houseId, Integer buildingId) throws BuildingNotFoundException, HouseNotFoundException {
        Sakancom.getBuildingById(buildingId).getHouseById(houseId).setInfoStatus(InfoStatus.ACCEPTED);
    }
    @When("the current admin try to get all updating houses")
    public void theCurrentAdminTryToGetAllUpdatingHouses() {
        updatedHouses = Admin.getAllUpdatedHouses();
    }
    @Then("the result size of the updating houses must be {int}")
    public void theResultSizeOfTheUpdatingHousesMustBe(Integer expectedResult) {
        Integer actualResult = updatedHouses.size();
        assertEquals(expectedResult, actualResult);
    }
}
