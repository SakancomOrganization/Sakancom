package controllers.user_general_operations;

import controllers.UserGeneralOperations;
import enums.HouseClassificationByGender;
import exceptions.AlreadyFoundElementException;
import exceptions.UnacceptableValueException;
import exceptions.UserNotFoundException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.*;

import static org.junit.Assert.assertEquals;

public class TestHousesSearch {
    private Services services;
    private int monthlyRent;
    private Location location;
    private HouseClassificationByGender houseClassificationByGender;
    private Building building;
    private House house;
    @Given("another house is added")
    public void anotherHouseIsAdded() throws UnacceptableValueException, UserNotFoundException {
        building = new Building(2,
                "Great Bed",
                Sakancom.getUserByUsername("haya-sam"),
                new Location("Nablus","JamalAbdAllNasser"));

        house = new House(2,
                new Services(true, true, true, true, true, 4, 2),
                2500,
                1,
                HouseClassificationByGender.FAMILY);

        try {
            building.addHouse(house);
            Sakancom.addBuilding(building);
        } catch (AlreadyFoundElementException alreadyFoundElementException) {
            // catch the exception
        }
    }
    @When("services are {string} {string} {string} {string} {string} {int} {int}")
    public void servicesAre(String withElectricity, String withWater, String hasInternet, String hasTelephone, String hasBalcony, Integer bedroomsNum, Integer bathroomsNum) {
        boolean withElectricityBool;
        boolean withWaterBool;
        boolean hasInternetBool;
        boolean hasTelephoneBool;
        boolean hasBalconyBool;
        withElectricityBool = withElectricity.equalsIgnoreCase("true");
        withWaterBool = withWater.equalsIgnoreCase("true");
        hasInternetBool = hasInternet.equalsIgnoreCase("true");
        hasTelephoneBool = hasTelephone.equalsIgnoreCase("true");
        hasBalconyBool = hasBalcony.equalsIgnoreCase("true");
        services = new Services(withElectricityBool, withWaterBool, hasInternetBool, hasTelephoneBool, hasBalconyBool, bedroomsNum, bathroomsNum);
    }
    @When("monthly rent is {int}")
    public void monthlyRentIs(Integer monthlyRent) {
        this.monthlyRent = monthlyRent;
    }
    @When("location is {string} {string}")
    public void locationIs(String city, String street) {
        location = new Location(city, street);
    }
    @When("house classification by gender is {string}")
    public void houseClassificationByGenderIs(String houseClassificationByGenderString) {
        if(houseClassificationByGenderString.equalsIgnoreCase("Family")) {
            this.houseClassificationByGender = HouseClassificationByGender.FAMILY;
        } else if(houseClassificationByGenderString.equalsIgnoreCase("Female")) {
            this.houseClassificationByGender = HouseClassificationByGender.FEMALE;
        } else if(houseClassificationByGenderString.equalsIgnoreCase("Male")) {
            this.houseClassificationByGender = HouseClassificationByGender.MALE;
        }
    }
    @Then("the resulted list size will be {int}")
    public void theResultedListSizeWillBe(Integer resultSize) {
        Integer actualSize = UserGeneralOperations.searchAboutHouses(services, monthlyRent, location, houseClassificationByGender).size();
        assertEquals(resultSize, actualSize);
    }
}
