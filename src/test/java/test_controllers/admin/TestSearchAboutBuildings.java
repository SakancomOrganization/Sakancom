package test_controllers.admin;

import controllers.Admin;
import exceptions.AlreadyFoundElementException;
import exceptions.UserNotFoundException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Building;
import models.Location;
import models.Name;
import models.Sakancom;

import static org.junit.Assert.assertEquals;

public class TestSearchAboutBuildings {
    private int buildingId;
    private String buildingName;
    private String ownerUsername;
    private Name name;
    private Location location;
    @Given("Another building is added")
    public void anotherBuildingIsAdded() throws UserNotFoundException, AlreadyFoundElementException {
        Building building = new Building(-1,
                "Selver House",
                Sakancom.getUserByUsername("haya-sam"),
                new Location("Nablus","JamalAbdAlNasser"));
        Sakancom.addBuilding(building);
    }
    @When("building id to search about is {int}")
    public void buildingIdToSearchAboutIs(Integer buildingId) {
        this.buildingId = buildingId;
    }
    @When("building name to search about is {string}")
    public void buildingNameToSearchAboutIs(String buildingName) {
        this.buildingName = buildingName;
    }
    @When("owner username is {string}")
    public void ownerUsernameIs(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }
    @When("owner name to search about is is {string} {string} {string}")
    public void ownerNameToSearchAboutIsIs(String firstName, String middleName, String lastName) {
        name = new Name(firstName, middleName, lastName);
    }
    @When("location to search about is {string} {string}")
    public void locationToSearchAboutIs(String city, String street) {
        location = new Location(city, street);
    }
    @Then("the result of searched buildings must be {int}")
    public void theResultOfSearchedBuildingsMustBe(Integer expectedResult) {
        Integer actualResult = Admin.searchAboutBuildings(buildingId, buildingName, ownerUsername, name, location).size();
        assertEquals(expectedResult, actualResult);
    }
}
