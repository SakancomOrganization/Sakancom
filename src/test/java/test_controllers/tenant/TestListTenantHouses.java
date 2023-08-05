package test_controllers.tenant;

import controllers.Tenant;
import enums.SaleStatus;
import exceptions.BuildingNotFoundException;
import exceptions.HouseNotFoundException;
import exceptions.UserNotFoundException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.House;
import models.Sakancom;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestListTenantHouses {
    private Map<Integer, List<House>> tenantHouses;
    @When("{string} is the current user")
    public void isTheCurrentUser(String username) throws UserNotFoundException {
        Sakancom.setCurrentUser(Sakancom.getUserByUsername(username));
    }
    @When("she bought house {int} in building {int}")
    public void sheRequestHouseInBuilding(Integer houseId, Integer buildingId) throws BuildingNotFoundException, HouseNotFoundException {
        Sakancom.getBuildingById(buildingId).getHouseById(houseId).getSaleContract().setTenant(Sakancom.getCurrentUser());
        Sakancom.getBuildingById(buildingId).getHouseById(houseId).getSaleContract().setSaleStatus(SaleStatus.UNAVAILABLE);
    }
    @When("she list her own houses as a tenant")
    public void sheListHerOwnHousesAsATenant() {
        tenantHouses = Tenant.listTenantHouses();
    }
    @Then("the result must be {int}")
    public void theResultMustBe(Integer expectedResult) {
        Integer actualResult = tenantHouses.size();
        assertEquals(expectedResult, actualResult);
    }
}
