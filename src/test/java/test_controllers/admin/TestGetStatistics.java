package test_controllers.admin;

import controllers.Admin;
import enums.SaleStatus;
import exceptions.BuildingNotFoundException;
import exceptions.HouseNotFoundException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Sakancom;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TestGetStatistics {
    private Map<String, Integer> statisticsMap;
    @When("the admin wants to see statistics about the app")
    public void theAdminWantsToSeeStatisticsAboutTheApp() {
        statisticsMap = Admin.getStatistics();
    }
    @Then("number of admins must be {int}")
    public void numberOfAdminsMustBe(Integer adminsNum) {
        assertEquals(adminsNum, statisticsMap.get("adminsNum"));
    }
    @Then("number of owners must be {int}")
    public void numberOfOwnersMustBe(Integer ownersNum) {
        assertEquals(ownersNum, statisticsMap.get("ownersNum"));
    }
    @Then("number of tenants must be {int}")
    public void numberOfTenantsMustBe(Integer tenantsNum) {
        assertEquals(tenantsNum, statisticsMap.get("tenantsNum"));
    }
    @Then("number of buildings must be {int}")
    public void numberOfBuildingsMustBe(Integer buildingsNum) {
        assertEquals(buildingsNum, statisticsMap.get("buildingsNum"));
    }
    @Then("number of houses must be {int}")
    public void numberOfHousesMustBe(Integer housesNum) {
        assertEquals(housesNum, statisticsMap.get("housesNum"));
    }
    @Then("number of available houses must be {int}")
    public void numberOfAvailableHousesMustBe(Integer availableHousesNum) {
        assertEquals(availableHousesNum, statisticsMap.get("availableHousesNum"));
    }
    @Then("number of unavailable houses must be {int}")
    public void numberOfUnavailableHousesMustBe(Integer unavailableHousesNum) {
        assertEquals(unavailableHousesNum, statisticsMap.get("unavailableHousesNum"));
    }
    @Then("number of requested houses must be {int}")
    public void numberOfRequestedHousesMustBe(Integer requestedHousesNum) {
        assertEquals(requestedHousesNum, statisticsMap.get("requestedHousesNum"));
    }
    @When("house {int} in building {int} is set to be unavailable")
    public void houseInBuildingIsSetToBeUnavailable(Integer houseId, Integer buildingId) throws BuildingNotFoundException, HouseNotFoundException {
        Sakancom.getBuildingById(buildingId).getHouseById(houseId).getSaleContract().setSaleStatus(SaleStatus.UNAVAILABLE);
        statisticsMap = Admin.getStatistics();
    }
    @When("house {int} in building {int} is set to be requested")
    public void houseInBuildingIsSetToBeRequested(Integer houseId, Integer buildingId) throws BuildingNotFoundException, HouseNotFoundException {
        Sakancom.getBuildingById(buildingId).getHouseById(houseId).getSaleContract().setSaleStatus(SaleStatus.REQUESTED);
        statisticsMap = Admin.getStatistics();
    }
}
