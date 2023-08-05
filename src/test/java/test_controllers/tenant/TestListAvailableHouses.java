package test_controllers.tenant;

import controllers.Tenant;
import enums.SaleStatus;
import exceptions.AlreadyFoundElementException;
import exceptions.InvalidEmailFormatException;
import exceptions.UnacceptableValueException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.House;
import models.Sakancom;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TestListAvailableHouses {
    private Map<Integer, List<House>> availableHouses;

    @Given("Database is already filled")
    public void databaseIsAlreadyFilled() throws UnacceptableValueException, ParseException, AlreadyFoundElementException, InvalidEmailFormatException {
        Sakancom.initSakancomWithData();
    }
    @Given("the house sale status is set to be requested")
    public void theHouseSaleStatusIsSetToBeRequested() {
        Sakancom.getBuildings().get(0).getHouses().get(0).getSaleContract().setSaleStatus(SaleStatus.REQUESTED);
    }
    @When("tenant wants to list all the available houses in the database")
    public void tenantWantsToListAllTheAvailableHousesInTheDatabase() {
        availableHouses = Tenant.listAvailableHouses();
    }
    @Then("the size of the available houses result {int}")
    public void theSizeOfTheResult(Integer expectedSize) {
        Integer actualSize = availableHouses.size();
        assertEquals(expectedSize, actualSize);
    }
}
