package views;

import controllers.UserGeneralOperations;
import enums.HouseClassificationByGender;
import exceptions.*;
import models.Location;
import models.Name;
import models.Services;

import java.text.ParseException;
import java.util.logging.Logger;

public class UserGeneralOperationsView {
    public static final String VALUE = "value";
    private static final Logger logger = Logger.getLogger(UserGeneralOperationsView.class.getName());
    private UserGeneralOperationsView() {

    }

    private static void printUserFields() {
        String outputString = "User fields: firstName - secondName - lastName - city - street - building - floor - email - phoneNumber - birthDate - major\n";
        logger.info(outputString);
    }

    public static void updateInfoView() {
        printUserFields();
        String field = CustomizedScanners.scanNonEmptyString("field");
        String value = CustomizedScanners.scanNonEmptyString(VALUE);
        while (true) {
            try {
                if(ViewsValidation.isValidUserField(field)) {
                    UserGeneralOperations.updateInfo(field, value);
                    logger.info("Updated Successfully");
                    break;
                } else {
                    logger.warning("Invalid field!");
                    field = CustomizedScanners.scanString("field");
                }
            } catch (ParseException e) {
                logger.warning("Invalid birthdate!");
                value = CustomizedScanners.scanString(VALUE);
            } catch (UnacceptableValueException e) {
                logger.warning("Invalid floor number!");
                value = CustomizedScanners.scanString(VALUE);
            } catch (WeakPasswordException e) {
                logger.warning("Weak password!");
                value = CustomizedScanners.scanString(VALUE);
            } catch (InvalidEmailFormatException e) {
                logger.warning("Invalid email!");
                value = CustomizedScanners.scanString(VALUE);
            }
        }
    }

    public static void rateHouseView() {
        int buildingId = CustomizedScanners.scanInt("building id");
        int houseId = CustomizedScanners.scanInt("house id");
        int rate = CustomizedScanners.scanInt("rate");
        while(true) {
            try {
                UserGeneralOperations.rateHouse(buildingId, houseId, rate);
                break;
            } catch (BuildingNotFoundException e) {
                logger.warning("Invalid building ID!");
                buildingId = CustomizedScanners.scanInt("building id");
            } catch (HouseNotFoundException e) {
                logger.warning("Invalid house ID!");
                houseId = CustomizedScanners.scanInt("house id");
            } catch (UnacceptableValueException e) {
                logger.warning("Invalid rate value (rate must be 0 and 5)!");
                rate = CustomizedScanners.scanInt("rate");
            }
        }
    }

    public static void searchAboutHousesView() {
        boolean withElectricity = CustomizedScanners.scanBoolean("with electricity");
        boolean withWater = CustomizedScanners.scanBoolean("with water");
        boolean hasInternet = CustomizedScanners.scanBoolean("has internet");
        boolean hasTelephone = CustomizedScanners.scanBoolean("has telephone");
        boolean hasBalcony = CustomizedScanners.scanBoolean("has balcony");
        int bedroomsNum = CustomizedScanners.scanInt("bedrooms number");
        int bathroomsNum = CustomizedScanners.scanInt("bathrooms number");
        int monthlyRent = CustomizedScanners.scanInt("monthly rent");
        String firstName = CustomizedScanners.scanString("owner first name");
        String middleName = CustomizedScanners.scanString("owner middle name");
        String lastName = CustomizedScanners.scanString("owner last name");
        String buildingName = CustomizedScanners.scanString("building name");
        String city = CustomizedScanners.scanString("city");
        String street = CustomizedScanners.scanString("street");
        HouseClassificationByGender houseClassificationByGender = CustomizedScanners.scanHouseClassificationByGender();

        while (true) {
            try {
                Services services = new Services(withElectricity, withWater, hasInternet, hasTelephone, hasBalcony, bedroomsNum, bathroomsNum);
                Name ownerName = new Name(firstName, middleName, lastName);
                Location location = new Location(city, street);
                CollectionsPrinter.printMap(UserGeneralOperations.searchAboutHouses(services, monthlyRent, ownerName, buildingName, location, houseClassificationByGender));
                break;
            } catch (UnacceptableValueException e) {
                if(ViewsValidation.isNegativeNumber(bedroomsNum)) {
                    bedroomsNum = CustomizedScanners.scanInt("bedrooms number");
                    logger.info("Invalid bedrooms number");
                }
                if(ViewsValidation.isNegativeNumber(bathroomsNum)) {
                    bathroomsNum = CustomizedScanners.scanInt("bathrooms number");
                    logger.info("Invalid bathrooms number");
                }
                if(ViewsValidation.isNegativeNumber(monthlyRent)) {
                    monthlyRent = CustomizedScanners.scanInt("monthly rent");
                    logger.info("Invalid monthly rent");
                }
            } catch (BuildingNotFoundException e) {
                logger.warning("Invalid building name!");
                buildingName = CustomizedScanners.scanString("building name");
            }
        }
    }
}
