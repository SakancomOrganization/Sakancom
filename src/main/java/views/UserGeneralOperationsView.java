package views;

import controllers.UserGeneralOperations;
import enums.HouseClassificationByGender;
import exceptions.*;
import models.Location;
import models.Name;
import models.Services;
import printers.CollectionsPrinter;
import scanners.CustomizedScanners;

import java.text.ParseException;
import java.util.Scanner;
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
        String field = CustomizedScanners.scanNonEmptyString("field", new Scanner(System.in));
        String value = CustomizedScanners.scanNonEmptyString(VALUE, new Scanner(System.in));
        while (true) {
            try {
                if(ViewsValidation.isValidUserField(field)) {
                    UserGeneralOperations.updateInfo(field, value);
                    logger.info("Updated Successfully");
                    break;
                } else {
                    logger.warning("Invalid field!");
                    field = CustomizedScanners.scanString("field", new Scanner(System.in));
                }
            } catch (ParseException e) {
                logger.warning("Invalid birthdate!");
                value = CustomizedScanners.scanString(VALUE, new Scanner(System.in));
            } catch (UnacceptableValueException e) {
                logger.warning("Invalid floor number!");
                value = CustomizedScanners.scanString(VALUE, new Scanner(System.in));
            } catch (WeakPasswordException e) {
                logger.warning("Weak password!");
                value = CustomizedScanners.scanString(VALUE, new Scanner(System.in));
            } catch (InvalidEmailFormatException e) {
                logger.warning("Invalid email!");
                value = CustomizedScanners.scanString(VALUE, new Scanner(System.in));
            }
        }
    }

    public static void rateHouseView() {
        int buildingId = CustomizedScanners.scanInt("building id", new Scanner(System.in));
        int houseId = CustomizedScanners.scanInt("house id", new Scanner(System.in));
        int rate = CustomizedScanners.scanInt("rate", new Scanner(System.in));
        while(true) {
            try {
                UserGeneralOperations.rateHouse(buildingId, houseId, rate);
                break;
            } catch (BuildingNotFoundException e) {
                logger.warning("Invalid building ID!");
                buildingId = CustomizedScanners.scanInt("building id", new Scanner(System.in));
            } catch (HouseNotFoundException e) {
                logger.warning("Invalid house ID!");
                houseId = CustomizedScanners.scanInt("house id", new Scanner(System.in));
            } catch (UnacceptableValueException e) {
                logger.warning("Invalid rate value (rate must be 0 and 5)!");
                rate = CustomizedScanners.scanInt("rate", new Scanner(System.in));
            }
        }
    }

    public static void searchAboutHousesView() {
        Services services = CustomizedScanners.scanServices(new Scanner(System.in));
        int monthlyRent = CustomizedScanners.scanInt("monthly rent", new Scanner(System.in));
        String firstName = CustomizedScanners.scanString("owner first name", new Scanner(System.in));
        String middleName = CustomizedScanners.scanString("owner middle name", new Scanner(System.in));
        String lastName = CustomizedScanners.scanString("owner last name", new Scanner(System.in));
        String buildingName = CustomizedScanners.scanString("building name", new Scanner(System.in));
        String city = CustomizedScanners.scanString("city", new Scanner(System.in));
        String street = CustomizedScanners.scanString("street", new Scanner(System.in));
        HouseClassificationByGender houseClassificationByGender = CustomizedScanners.scanHouseClassificationByGender(new Scanner(System.in));

        while (true) {
            try {
                if(ViewsValidation.isNegativeNumber(monthlyRent)) {
                    monthlyRent = CustomizedScanners.scanInt("monthly rent", new Scanner(System.in));
                    logger.info("Invalid monthly rent");
                } else {
                    Name ownerName = new Name(firstName, middleName, lastName);
                    Location location = new Location(city, street);
                    CollectionsPrinter.printMap(UserGeneralOperations.searchAboutHouses(services, monthlyRent, ownerName, buildingName, location, houseClassificationByGender));
                    break;
                }
            } catch (BuildingNotFoundException e) {
                logger.warning("Invalid building name!");
                buildingName = CustomizedScanners.scanString("building name", new Scanner(System.in));
            }
        }
    }
}
