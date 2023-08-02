package test_controllers.admin;

import controllers.Admin;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Building;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestListAllBuildings {
    private List<Building> buildings;
    @When("the admin wants to list all buildings")
    public void theAdminWantsToListAllBuildings() {
        buildings = Admin.listAllBuildings();
    }
    @Then("the result size of all the buildings must be {int}")
    public void theResultSizeOfAllTheBuildingsMustBe(Integer expectedSize) {
        Integer actualSize = buildings.size();
        assertEquals(expectedSize, actualSize);
    }
}
