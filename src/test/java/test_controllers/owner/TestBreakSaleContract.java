package test_controllers.owner;

import controllers.Owner;
import enums.SaleStatus;
import exceptions.BuildingNotFoundException;
import exceptions.HouseNotFoundException;
import exceptions.UserNotFoundException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Sakancom;

import static org.junit.Assert.assertEquals;

public class TestBreakSaleContract {
    private int buildingId;
    private int houseId;

    @When("current owner who wants to break a sale contract is {string}")
    public void currentOwnerWhoWantsToBreakASaleContractIs(String username) throws UserNotFoundException {
        Sakancom.setCurrentUser(Sakancom.getUserByUsername(username));
    }
    @When("sale status of {int} in {int} is set to be unavailable")
    public void saleStatusOfInIsSetToBeUnavailable(Integer houseId, Integer buildingId) {
        this.buildingId = buildingId;
        this.houseId = houseId;
    }
    @When("owner break the sale request")
    public void ownerBreakTheSaleRequest() throws BuildingNotFoundException, HouseNotFoundException {
        Owner.breakSaleStatus(buildingId, houseId);
    }
    @Then("the sale of status of this house must be available")
    public void theSaleOfStatusOfThisHouseMustBeAvailable() throws BuildingNotFoundException, HouseNotFoundException {
        assertEquals(SaleStatus.AVAILABLE, Sakancom.getBuildingById(buildingId).getHouseById(houseId).getSaleContract().getSaleStatus());
    }
}
