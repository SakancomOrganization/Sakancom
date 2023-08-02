package test_controllers.tenant;

import controllers.Tenant;
import enums.SaleStatus;
import exceptions.BuildingNotFoundException;
import exceptions.HouseNotFoundException;
import exceptions.UserNotFoundException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.House;
import models.Sakancom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TestLeaveHouse {
    private int houseId;
    private int buildingId;
    @Given("house {int} in building {int} is assigned to {string}")
    public void houseInBuildingIsAssignedTo(Integer houseId, Integer buildingId, String username) throws BuildingNotFoundException, HouseNotFoundException, UserNotFoundException {
        House house = Sakancom.getBuildingById(buildingId).getHouseById(houseId);
        house.getSaleContract().setTenant(Sakancom.getUserByUsername(username));
        house.getSaleContract().setSaleStatus(SaleStatus.UNAVAILABLE);
    }
    @When("current tenant who wants to leave a house is {string}")
    public void currentTenantWhoWantsToLeaveAHouseIs(String username) throws UserNotFoundException {
        Sakancom.setCurrentUser(Sakancom.getUserByUsername(username));
    }
    @When("sale status of {int} in {int} is unavailable")
    public void saleStatusOfInIsUnavailable(Integer houseId, Integer buildingId) {
        this.houseId = houseId;
        this.buildingId = buildingId;
    }
    @When("tenant left the house")
    public void tenantLeftTheHouse() throws BuildingNotFoundException, HouseNotFoundException {
        Tenant.leaveHouse(buildingId, houseId);
    }
    @Then("the sale of status of this house left by the tenant must be available")
    public void theSaleOfStatusOfThisHouseLeftByTheTenantMustBeAvailable() throws BuildingNotFoundException, HouseNotFoundException {
        assertEquals(SaleStatus.AVAILABLE, Sakancom.getBuildingById(buildingId).getHouseById(houseId).getSaleContract().getSaleStatus());
    }
    @Then("the sale status must not change and building not found exception will be thrown")
    public void theSaleStatusMustNotChangeAndBuildingNotFoundExceptionWillBeThrown() {
        assertThrows(BuildingNotFoundException.class, () -> Tenant.leaveHouse(buildingId, houseId));
    }
    @Then("the sale status must not change and house not found exception will be thrown")
    public void theSaleStatusMustNotChangeAndHouseNotFoundExceptionWillBeThrown() {
        assertThrows(HouseNotFoundException.class, () -> Tenant.leaveHouse(buildingId, houseId));
    }
}
