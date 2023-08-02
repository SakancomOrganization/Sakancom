package test_controllers.owner;

import controllers.Owner;
import enums.SaleStatus;
import exceptions.BuildingNotFoundException;
import exceptions.HouseNotFoundException;
import exceptions.UserNotFoundException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.House;
import models.Sakancom;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestGetAllSaleRequests {
    private List<House> saleRequests;
    @When("current owner who wants to get all the sale requests is {string}")
    public void currentOwnerWhoWantsToGetAllTheSaleRequestsIs(String username) throws UserNotFoundException {
        Sakancom.setCurrentUser(Sakancom.getUserByUsername(username));
    }
    @When("the sale status of {int} in {int} is set to be requested")
    public void theSaleStatusOfInIsSetToBeRequested(Integer houseId, Integer buildingId) throws BuildingNotFoundException, HouseNotFoundException {
        Sakancom.getBuildingById(buildingId).getHouseById(houseId).getSaleContract().setSaleStatus(SaleStatus.REQUESTED);
    }
    @When("the sale status of {int} in {int} is set to be available")
    public void theSaleStatusOfInIsSetToBeAvailable(Integer houseId, Integer buildingId) throws BuildingNotFoundException, HouseNotFoundException {
        Sakancom.getBuildingById(buildingId).getHouseById(houseId).getSaleContract().setSaleStatus(SaleStatus.AVAILABLE);

    }
    @When("owner select to get all sales requests")
    public void ownerSelectToGetAllSalesRequests() {
        saleRequests = Owner.getAllSaleRequests();
    }
    @Then("the result size of the sales requests must be {int}")
    public void theResultSizeOfTheSalesRequestsMustBe(Integer expectedSize) {
        Integer actualSize = saleRequests.size();
        assertEquals(expectedSize, actualSize);
    }
}
