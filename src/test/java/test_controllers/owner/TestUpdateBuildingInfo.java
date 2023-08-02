package test_controllers.owner;

import controllers.Owner;
import exceptions.BuildingNotFoundException;
import exceptions.UserNotFoundException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Building;
import models.Sakancom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TestUpdateBuildingInfo {
    private int buildingId;
    private String field;
    private String value;
    @When("current owner who wants to update the building info is {string}")
    public void currentOwnerWhoWantsToUpdateTheBuildingInfoIs(String username) throws UserNotFoundException {
        Sakancom.setCurrentUser(Sakancom.getUserByUsername(username));
    }
    @When("the buildingId is {int}")
    public void theBuildingIdIs(Integer buildingId) {
        this.buildingId = buildingId;
    }
    @When("the field of updating the building is {string}")
    public void theFieldIs(String field) {
        this.field = field;
    }
    @When("the value of updating the building is {string}")
    public void theValueIs(String value) {
        this.value = value;
    }
    @Then("the building information will be updated successfully")
    public void theBuildingInformationWillBeUpdatedSuccessfully() throws BuildingNotFoundException {
        Owner.updateBuildingInfo(buildingId, field, value);
        Building building = Sakancom.getBuildingById(buildingId);
        if(field.equalsIgnoreCase("name")) {
            assertEquals("Great Building", building.getName());
        } else if(field.equalsIgnoreCase("city")) {
            assertEquals("Ramallah", building.getLocation().getCity());
        } else if(field.equalsIgnoreCase("street")) {
            assertEquals("AlManara", building.getLocation().getStreet());
        }
    }
    @Then("the building information will not be updated successfully and building not found exception will be thrown")
    public void theBuildingInformationWillNotBeUpdatedSuccessfullyAndBuildingNotFoundExceptionWillBeThrown() {
        assertThrows(BuildingNotFoundException.class, () -> Owner.updateBuildingInfo(buildingId, field, value));
    }
}
