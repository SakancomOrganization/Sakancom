package test_controllers.owner;

import controllers.Owner;
import exceptions.AlreadyFoundElementException;
import exceptions.BuildingNotFoundException;
import exceptions.HouseNotFoundException;
import exceptions.UserNotFoundException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Sakancom;

import static org.junit.Assert.*;

public class TestAddImage {
    private int buildingId;
    private int houseId;
    private String image;
    @When("current owner who wants to add an image is {string}")
    public void currentOwnerWhoWantsToAddAnImageIs(String username) throws UserNotFoundException {
        Sakancom.setCurrentUser(Sakancom.getUserByUsername(username));
    }
    @When("building contains the house of the added image is {int}")
    public void buildingContainsTheHouseOfTheAddedImageIs(Integer buildingId) {
        this.buildingId = buildingId;
    }
    @When("house contains the added image is {int}")
    public void houseContainsTheAddedImageIs(Integer houseId) {
        this.houseId = houseId;
    }
    @When("{string} is already added to {int} in {int}")
    public void isAlreadyAddedToIn(String image, Integer houseId, Integer buildingId) throws AlreadyFoundElementException, BuildingNotFoundException, HouseNotFoundException {
        Owner.addImage(buildingId, houseId, image);
    }
    @When("image is {string}")
    public void imageIs(String image) {
        this.image = image;
    }
    @Then("the image will be added successfully")
    public void theImageWillBeAddedSuccessfully() throws AlreadyFoundElementException, BuildingNotFoundException, HouseNotFoundException {
        Owner.addImage(buildingId, houseId, image);
        assertTrue(Sakancom.getBuildingById(buildingId).getHouseById(houseId).getImages().contains(image));
    }
    @Then("the image will not be added successfully and building not found exception will be thrown")
    public void theImageWillNotBeAddedSuccessfullyAndBuildingNotFoundExceptionWillBeThrown() {
        assertThrows(BuildingNotFoundException.class, () -> Owner.addImage(buildingId, houseId, image));
    }
    @Then("the image will not be added successfully and house not found exception will be thrown")
    public void theImageWillNotBeAddedSuccessfullyAndHouseNotFoundExceptionWillBeThrown() {
        assertThrows(HouseNotFoundException.class, () -> Owner.addImage(buildingId, houseId, image));
    }
    @Then("the image will not be added successfully and already found exception element will thrown")
    public void theImageWillNotBeAddedSuccessfullyAndAlreadyFoundExceptionElementWillThrown() {
        assertThrows(AlreadyFoundElementException.class, () -> Owner.addImage(buildingId, houseId, image));
    }
}
