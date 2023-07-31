package test_controllers.tenant;

import controllers.Tenant;
import enums.HouseClassificationByGender;
import exceptions.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.*;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TestGetAllNeighbors {
    private int buildingId;
    private int houseId;
    private List<User> neighbors;
    @Given("another building with two houses is added")
    public void anotherBuildingWithTwoHousesIsAdded() throws UserNotFoundException, UnacceptableValueException, BuildingNotFoundException {
        // add a house
        House oldBuildingNewHouse = new House(-1,
                new Services(true, true, true, true, true, 3, 2),
                2000,
                1,
                HouseClassificationByGender.FAMILY);

        // add a building
        Building newBuilding = new Building(-1,
                "Golden House",
                Sakancom.getUserByUsername("haya-sam"),
                new Location("Nablus","Rafidia"));

        // add a house to the new building
        House newBuildingNewHouse = new House(-1,
                new Services(true, true, true, true, true, 3, 2),
                2000,
                1,
                HouseClassificationByGender.FAMILY);

        try {
            Sakancom.getBuildingById(1).addHouse(oldBuildingNewHouse);
            Sakancom.addBuilding(newBuilding);
            newBuilding.addHouse(newBuildingNewHouse);
        } catch (AlreadyFoundElementException alreadyFoundElementException) {
            // catch the exception
        }
    }
    @When("building id is {int}")
    public void buildingIdIs(Integer buildingId) {
        this.buildingId = buildingId;
    }
    @When("house id is {int}")
    public void houseIdIs(Integer houseId) {
        this.houseId = houseId;
    }
    @When("tenant wants to list all the neighbors houses in the database")
    public void tenantWantsToListAllTheNeighborsHousesInTheDatabase() throws BuildingNotFoundException, HouseNotFoundException {
        neighbors = Tenant.getAllHouseNeighbors(buildingId, houseId);
    }
    @Then("the size of the neighbors result {int}")
    public void theSizeOfTheNeighborsResult(Integer expectedSize) {
        Integer actualSize = neighbors.size();
        assertEquals(expectedSize, actualSize);
    }
    @Then("the neighbors will not be found and building not found exception will be thrown")
    public void theNeighborsWillNotBeFoundAndBuildingNotFoundExceptionWillBeThrown() {
        assertThrows(BuildingNotFoundException.class, () -> {
            Tenant.getAllHouseNeighbors(buildingId, houseId);
        });
    }
    @Then("the neighbors will not be found and house not found exception will be thrown")
    public void theNeighborsWillNotBeFoundAndHouseNotFoundExceptionWillBeThrown() {
        assertThrows(HouseNotFoundException.class, () -> {
            Tenant.getAllHouseNeighbors(buildingId, houseId);
        });
    }
}
