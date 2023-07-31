package test_controllers.user_general_operations;

import controllers.UserGeneralOperations;
import exceptions.BuildingNotFoundException;
import exceptions.HouseNotFoundException;
import exceptions.UnacceptableValueException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.House;
import models.Sakancom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TestRatingHouse {

    private int buildingId;
    private int houseId;
    private int newRate;

    @When("building id is {int}")
    public void buildingIdIs(Integer buildingId) {
        this.buildingId = buildingId;
    }
    @When("house id is {int}")
    public void houseIdIs(Integer houseId) {
        this.houseId = houseId;
    }
    @When("new rate is {int}")
    public void newRateIs(Integer newRate) {
        this.newRate = newRate;
    }
    @Then("the house rate will be {double}")
    public void theHouseRateWillBe(Double updatedRate) throws UnacceptableValueException, HouseNotFoundException, BuildingNotFoundException {
        UserGeneralOperations.rateHouse(buildingId, houseId, newRate);
        House house = Sakancom.getBuildingById(buildingId).getHouseById(houseId);
        assertEquals(updatedRate, house.getHouseRate().getTotalRate(), 0.0);

    }
    @Then("a Building Not Found Exception will be thrown")
    public void aBuildingNotFoundExceptionWillBeThrown() {
        assertThrows(BuildingNotFoundException.class, () -> {
            UserGeneralOperations.rateHouse(buildingId, houseId, newRate);
        });
    }
    @Then("a House Not Found Exception will be thrown")
    public void aHouseNotFoundExceptionWillBeThrown() {
        assertThrows(HouseNotFoundException.class, () -> {
            UserGeneralOperations.rateHouse(buildingId, houseId, newRate);
        });
    }
    @Then("a Number Format Exception Will be thrown")
    public void aNumberFormatExceptionWillBeThrown() {
        assertThrows(UnacceptableValueException.class, () -> {
            UserGeneralOperations.rateHouse(buildingId, houseId, newRate);
        });
    }
}
