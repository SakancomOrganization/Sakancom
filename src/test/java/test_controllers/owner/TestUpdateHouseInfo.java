package test_controllers.owner;

import controllers.Owner;
import enums.HouseClassificationByGender;
import exceptions.BuildingNotFoundException;
import exceptions.HouseNotFoundException;
import exceptions.UnacceptableValueException;
import exceptions.UserNotFoundException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.House;
import models.Sakancom;

import static org.junit.Assert.*;

public class TestUpdateHouseInfo {
    private int buildingId;
    private int houseId;
    private String field;
    private String value;
    @When("current owner who wants to update the house info is {string}")
    public void currentOwnerWhoWantsToUpdateTheHouseInfoIs(String username) throws UserNotFoundException {
        Sakancom.setCurrentUser(Sakancom.getUserByUsername(username));
    }
    @When("building id of the updatedHouse building is {int}")
    public void buildingIdOfTheUpdatedHouseBuildingIs(Integer buildingId) {
        this.buildingId = buildingId;
    }
    @When("updated house id is {int}")
    public void updatedHouseIdIs(Integer houseId) {
        this.houseId = houseId;
    }
    @When("field of updating the house is {string}")
    public void fieldOfUpdatingTheHouseIs(String field) {
        this.field = field;
    }
    @When("value of updating the house is {string}")
    public void valueOfUpdatingTheHouseIs(String value) {
        this.value = value;
    }
    @Then("the house must be updated successfully")
    public void theHouseMustBeUpdatedSuccessfully() throws BuildingNotFoundException, HouseNotFoundException, UnacceptableValueException {
        Owner.updateHouseInfo(buildingId, houseId, field, value);
        House house = Sakancom.getBuildingById(buildingId).getHouseById(houseId);
        if(field.equalsIgnoreCase("monthlyRent")) {
            assertEquals(3400, house.getMonthlyRent());
        } else if(field.equalsIgnoreCase("includesElectricity")) {
            assertFalse(house.getServices().isIncludesElectricity());
        } else if(field.equalsIgnoreCase("includesWater")) {
            assertFalse(house.getServices().isIncludesWater());
        } else if(field.equalsIgnoreCase("hasInternet")) {
            assertTrue(house.getServices().isHasInternet());
        } else if(field.equalsIgnoreCase("hasTelephone")) {
            assertFalse(house.getServices().isHasTelephone());
        } else if(field.equalsIgnoreCase("hasBalcony")) {
            assertTrue(house.getServices().isHasBalcony());
        } else if(field.equalsIgnoreCase("bedroomsNum")) {
            assertEquals(5, house.getServices().getBedroomsNum());
        } else if(field.equalsIgnoreCase("bathroomsNum")) {
            assertEquals(1, house.getServices().getBathroomsNum());
        } else if(field.equalsIgnoreCase("houseClassificationByGender")) {
            if(value.equalsIgnoreCase("Family")) {
                assertEquals(HouseClassificationByGender.FAMILY, house.getHouseClassificationByGender());
            } else if(value.equalsIgnoreCase("Female")) {
                assertEquals(HouseClassificationByGender.FEMALE, house.getHouseClassificationByGender());
            } else if(value.equalsIgnoreCase("Male")) {
                assertEquals(HouseClassificationByGender.MALE, house.getHouseClassificationByGender());
            }
        }
    }
    @Then("the house will not be updated and a number format exception will be thrown")
    public void theHouseWillNotBeUpdatedAndANumberFormatExceptionWillBeThrown() {
        assertThrows(NumberFormatException.class, () -> Owner.updateHouseInfo(buildingId, houseId, field, value));
    }
    @Then("the house will not be updated and a unacceptable value exception will be thrown")
    public void theHouseWillNotBeUpdatedAndAUnacceptableValueExceptionWillBeThrown() {
        assertThrows(UnacceptableValueException.class, () -> Owner.updateHouseInfo(buildingId, houseId, field, value));
    }
    @Then("the house will not be updated and a building not found will be thrown")
    public void theHouseWillNotBeUpdatedAndABuildingNotFoundWillBeThrown() {
        assertThrows(BuildingNotFoundException.class, () -> Owner.updateHouseInfo(buildingId, houseId, field, value));
    }
    @Then("the house will not be updated and a house not found will be thrown")
    public void theHouseWillNotBeUpdatedAndAHouseNotFoundWillBeThrown() {
        assertThrows(HouseNotFoundException.class, () -> Owner.updateHouseInfo(buildingId, houseId, field, value));
    }
}
