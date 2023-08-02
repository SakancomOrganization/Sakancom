package test_controllers.owner;

import controllers.Owner;
import enums.HouseClassificationByGender;
import exceptions.AlreadyFoundElementException;
import exceptions.BuildingNotFoundException;
import exceptions.UnacceptableValueException;
import exceptions.UserNotFoundException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.House;
import models.Sakancom;
import models.Services;

import static org.junit.Assert.*;

public class TestAddHouse {
    private int buildingId;
    private String username;
    private Services services;
    private int monthlyRent;
    private int floorNum;
    private boolean withElectricityBoolean;
    private boolean withWaterBoolean;
    private boolean hasInternetBoolean;
    private boolean hasTelephoneBoolean;
    private boolean hasBalconyBoolean;
    private int bedroomsNum;
    private int bathroomsNum;
    private HouseClassificationByGender houseClassificationByGender;

    @When("house added to building with id {int}")
    public void houseAddedToBuildingWithId(Integer buildingId) {
        this.buildingId = buildingId;
    }
    @When("current owner who wants to add a house is {string}")
    public void currentOwnerWhoWantsToAddAHouseIs(String username) throws UserNotFoundException {
        Sakancom.setCurrentUser(Sakancom.getUserByUsername(username));
    }
    @When("added house services are {string} {string} {string} {string} {string} {int} {int}")
    public void addedHouseServicesAre(String withElectricity, String withWater, String hasInternet, String hasTelephone, String hasBalcony, Integer bedroomsNum, Integer bathroomsNum) throws UnacceptableValueException {
        this.withElectricityBoolean = Boolean.parseBoolean(withElectricity);
        this.withWaterBoolean = Boolean.parseBoolean(withWater);
        this.hasInternetBoolean = Boolean.parseBoolean(hasInternet);
        this.hasTelephoneBoolean = Boolean.parseBoolean(hasTelephone);
        this.hasBalconyBoolean = Boolean.parseBoolean(hasBalcony);
        this.bedroomsNum = bedroomsNum;
        this.bathroomsNum = bathroomsNum;
    }
    @When("monthlyRent is {int}")
    public void monthlyRentIs(Integer monthlyRent) {
        this.monthlyRent = monthlyRent;
    }
    @When("floor num is {int}")
    public void floorNumIs(Integer floorNum) {
        this.floorNum = floorNum;
    }
    @When("house classification by gender of the added house is {string}")
    public void houseClassificationByGenderOfTheAddedHouseIs(String houseClassificationByGender) {
        if(houseClassificationByGender.equalsIgnoreCase("Family")) {
            this.houseClassificationByGender = HouseClassificationByGender.FAMILY;
        } else if(houseClassificationByGender.equalsIgnoreCase("Female")) {
            this.houseClassificationByGender = HouseClassificationByGender.FEMALE;
        } else if(houseClassificationByGender.equalsIgnoreCase("Male")) {
            this.houseClassificationByGender = HouseClassificationByGender.MALE;
        }
    }
    @Then("house will be added successfully")
    public void houseWillBeAddedSuccessfully() throws UnacceptableValueException, AlreadyFoundElementException, BuildingNotFoundException {
        services = new Services(withElectricityBoolean, withWaterBoolean, hasInternetBoolean, hasTelephoneBoolean, hasBalconyBoolean, bedroomsNum, bathroomsNum);
        House house = new House(-1, services, monthlyRent, floorNum, houseClassificationByGender);
        Owner.addHouse(buildingId, house);
        assertTrue(Sakancom.getBuildingById(buildingId).getHouses().contains(house));
    }
    @Then("house will not be added successfully and building not found exception will be thrown")
    public void houseWillNotBeAddedSuccessfullyAndBuildingNotFoundExceptionWillBeThrown() {
        assertThrows(BuildingNotFoundException.class, () -> {
            services = new Services(withElectricityBoolean, withWaterBoolean, hasInternetBoolean, hasTelephoneBoolean, hasBalconyBoolean, bedroomsNum, bathroomsNum);
            Owner.addHouse(buildingId, new House(-1, services, monthlyRent, floorNum, houseClassificationByGender));
        });
    }
    @Then("house will not be added successfully and unacceptable value exception will be thrown")
    public void houseWillNotBeAddedSuccessfullyAndUnacceptableValueExceptionWillBeThrown() {
        assertThrows(UnacceptableValueException.class,() -> {
            services = new Services(withElectricityBoolean, withWaterBoolean, hasInternetBoolean, hasTelephoneBoolean, hasBalconyBoolean, bedroomsNum, bathroomsNum);
            House house = new House(-1, services, monthlyRent, floorNum, houseClassificationByGender);
           Owner.addHouse(buildingId, house);
        });
    }
}
