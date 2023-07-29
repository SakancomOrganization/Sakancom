package controllers.user_general_operations;

import controllers.UserGeneralOperations;
import exceptions.UnacceptableValueException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Building;
import models.House;
import models.Sakancom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TestRateHouse {

    private int buildingId;
    private int houseId;
    private double newRate;

    @When("building id is {int}")
    public void buildingIdIs(Integer buildingId) {
        this.buildingId = buildingId;
    }
    @When("house id is {int}")
    public void houseIdIs(Integer houseId) {
        this.houseId = houseId;
    }
    @When("new rate is {double}")
    public void newRateIs(Double newRate) {
        this.newRate = newRate;
    }
    @Then("the house rate will be {double}")
    public void theHouseRateWillBe(Double updatedRate) throws UnacceptableValueException {
        UserGeneralOperations.rateHouse(buildingId, houseId, newRate);
        Building building = Sakancom.getBuildingById(buildingId);
        if(building != null) {
            House house = building.getHouseById(houseId);
            if(house != null) {
                assertEquals(updatedRate, house.getHouseRate().getRate(), 0.0);
            }
        }
    }
    @Then("a Null Pointer Exception Will be thrown")
    public void aNullPointerExceptionWillBeThrown() {
        assertThrows(NullPointerException.class, () -> {
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
