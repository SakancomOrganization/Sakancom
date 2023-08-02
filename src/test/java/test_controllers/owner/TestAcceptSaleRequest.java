package test_controllers.owner;

import controllers.Owner;
import enums.SaleStatus;
import exceptions.BuildingNotFoundException;
import exceptions.HouseNotFoundException;
import exceptions.UserNotFoundException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Sakancom;

import static org.junit.Assert.*;

public class TestAcceptSaleRequest {
    private int buildingId;
    private int houseId;
    @When("current owner who wants to accept a sale request is {string}")
    public void currentOwnerWhoWantsToAcceptASaleRequestIs(String username) throws UserNotFoundException {
        Sakancom.setCurrentUser(Sakancom.getUserByUsername(username));
    }
    @When("sale status of {int} in {int} is set to be requested")
    public void saleStatusOfInIsSetToBeRequested(Integer houseId, Integer buildingId) throws BuildingNotFoundException, HouseNotFoundException {
        this.buildingId = buildingId;
        this.houseId = houseId;
        Sakancom.getBuildingById(buildingId).getHouseById(houseId).getSaleContract().setSaleStatus(SaleStatus.REQUESTED);
    }
    @When("house to change its sale status is {int} and comes in {int}")
    public void houseToChangeItsSaleStatusIsAndComesIn(Integer houseId, Integer buildingId) {
        this.buildingId = buildingId;
        this.houseId = houseId;
    }
    @When("owner accept the sale request")
    public void ownerAcceptTheSaleRequest() throws BuildingNotFoundException, HouseNotFoundException {
        Owner.acceptSaleRequest(buildingId, houseId);
    }
    @Then("the sale of status of this house must be unavailable")
    public void theSaleOfStatusOfThisHouseMustBeUnavailable() throws BuildingNotFoundException, HouseNotFoundException {
        assertEquals(SaleStatus.UNAVAILABLE, Sakancom.getBuildingById(buildingId).getHouseById(houseId).getSaleContract().getSaleStatus());
    }
    @Then("the sale of status of this house must not change and building not found exception will be thrown")
    public void theSaleOfStatusOfThisHouseMustNotChangeAndBuildingNotFoundExceptionWillBeThrown() {
        assertThrows(BuildingNotFoundException.class, () -> Owner.acceptSaleRequest(buildingId, houseId));
    }
    @Then("the sale of status of this house must not change and house not found exception will be thrown")
    public void theSaleOfStatusOfThisHouseMustNotChangeAndHouseNotFoundExceptionWillBeThrown() {
        assertThrows(HouseNotFoundException.class, () -> Owner.acceptSaleRequest(buildingId, houseId));
    }
    @Then("the sale of status of this house must not change")
    public void theSaleOfStatusOfThisHouseMustNotChange() throws BuildingNotFoundException, HouseNotFoundException {
        assertNotEquals(SaleStatus.UNAVAILABLE, Sakancom.getBuildingById(buildingId).getHouseById(houseId).getSaleContract().getSaleStatus());
    }
}
