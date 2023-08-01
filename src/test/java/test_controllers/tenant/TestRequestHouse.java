package test_controllers.tenant;

import controllers.Tenant;
import enums.SaleStatus;
import exceptions.BuildingNotFoundException;
import exceptions.HouseNotFoundException;
import exceptions.UserNotFoundException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Sakancom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TestRequestHouse {
    private int buildingId;
    private int houseId;

    @When("building id of the requested house is {int}")
    public void buildingIdOfTheRequestedHouseIs(Integer buildingId) {
        this.buildingId = buildingId;
    }
    @When("house id of the requested house is {int}")
    public void houseIdOfTheRequestedHouseIs(Integer houseId) {
        this.houseId = houseId;
    }
    @When("the current tenant username is {string}")
    public void theTenantUsernameIs(String username) throws UserNotFoundException {
        Sakancom.setCurrentUser(Sakancom.getUserByUsername(username));
    }
    @Then("the sale status in the house sale contract of the house will be requested")
    public void theSaleStatusInTheHouseSaleContractOfTheHouseWillBeRequested() throws BuildingNotFoundException, HouseNotFoundException {
        Tenant.requestHouse(buildingId, houseId);
        assertEquals(SaleStatus.REQUESTED, Sakancom.getBuildingById(buildingId).getHouseById(houseId).getSaleContract().getSaleStatus());
    }
    @Then("the tenant in the house sale contract will be correct")
    public void theTenantInTheHouseSaleContractWillBeCorrect() throws BuildingNotFoundException, HouseNotFoundException {
        assertEquals(Sakancom.getCurrentUser(), Sakancom.getBuildingById(buildingId).getHouseById(houseId).getSaleContract().getTenant());
    }
    @Then("the request failed and building not found exception will be thrown")
    public void theRequestFailedAndBuildingNotFoundExceptionWillBeThrown() {
        assertThrows(BuildingNotFoundException.class, () -> Tenant.requestHouse(buildingId, houseId));
    }
    @Then("the request failed and house not found exception will be thrown")
    public void theRequestFailedAndHouseNotFoundExceptionWillBeThrown() {
        assertThrows(HouseNotFoundException.class, () -> Tenant.requestHouse(buildingId, houseId));
    }
}
