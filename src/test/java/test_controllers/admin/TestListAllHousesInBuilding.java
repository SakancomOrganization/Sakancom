package test_controllers.admin;

import controllers.Admin;
import exceptions.BuildingNotFoundException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TestListAllHousesInBuilding {
    private int buildingId;
    @When("admin wants to list all the houses in building {int}")
    public void adminWantsToListAllTheHousesInBuilding(Integer buildingId) {
        this.buildingId = buildingId;
    }
    @Then("the result size must be {int}")
    public void theResultSizeMustBe(Integer expectedSize) throws BuildingNotFoundException {
        Integer actualSie = Admin.listAllHousesInBuilding(buildingId).size();
        assertEquals(expectedSize, actualSie);
    }
    @Then("there will be no houses list and building not found exception will be thrown")
    public void thereWillBeNoHousesListAndBuildingNotFoundExceptionWillBeThrown() {
        assertThrows(BuildingNotFoundException.class, () -> Admin.listAllHousesInBuilding(buildingId));
    }
}
