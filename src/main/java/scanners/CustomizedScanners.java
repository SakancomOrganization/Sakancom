package scanners;

import enums.HouseClassificationByGender;
import enums.UserType;
import exceptions.UnacceptableValueException;
import models.Services;
import helpers.ViewsValidation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.*;

public class CustomizedScanners {
    private static final Logger logger = Logger.getLogger(CustomizedScanners.class.getName());

    private CustomizedScanners() {

    }

    private static void printScanMsg(String type) {
        String scanMsg = "Enter " + type + " :";
        logger.info(scanMsg);
    }

    private static void printWarnMsg(String type) {
        String warnMsg = "invalid " + type + "! Please, enter valid one";
        logger.warning(warnMsg);
    }

    public static int scanInt(String type, Scanner scanner) {
        printScanMsg(type);
        try {
            return scanner.nextInt();
        } catch (InputMismatchException inputMismatchException) {
            scanner.next();
            printWarnMsg(type);
            return -1; // invalid value
        }
    }

    public static String scanNonEmptyString(String type, Scanner scanner) {
        printScanMsg(type);
        String scanString;
        scanString = scanner.nextLine();
        if (!scanString.isEmpty())
            return scanString;
        printWarnMsg(type);
        return null; // invalid value
    }

    public static String scanString(String type, Scanner scanner) {
        printScanMsg(type);
        return scanner.nextLine();
    }

    public static boolean scanBoolean(String type, Scanner scanner) {
        printScanMsg(type);
        try {
            return scanner.nextBoolean();
        } catch (InputMismatchException inputMismatchException) {
            scanner.next();
            printWarnMsg(type);
            return false;
        }
    }

    public static Date scanBirthdate(Scanner scanner) {
        printScanMsg("birthdate");
        try {
            String scanBirthdateAsString = scanner.nextLine();
            return new SimpleDateFormat("dd/MM/yyyy").parse(scanBirthdateAsString);
        } catch (ParseException e) {
            printWarnMsg("birthdate");
            return null;
        }
    }

    private static UserType stringToUserType(String userType) {
        if (userType.equalsIgnoreCase("admin"))
            return UserType.ADMIN;
        else if (userType.equalsIgnoreCase("owner"))
            return UserType.OWNER;
        else if (userType.equalsIgnoreCase("tenant"))
            return UserType.TENANT;
        return null;
    }

    public static UserType scanUserType(Scanner scanner) {
        printScanMsg("user type");
        UserType scanUserType;
        String scanUserTypeAsString = scanner.nextLine();
        scanUserType = stringToUserType(scanUserTypeAsString);
        if (scanUserType == null) {
            printWarnMsg("user type");
        }
        return scanUserType;
    }

    private static HouseClassificationByGender stringToHouseClassificationByGender(String houseClassificationByGender) {
        if (houseClassificationByGender.equalsIgnoreCase("family"))
            return HouseClassificationByGender.FAMILY;
        else if (houseClassificationByGender.equalsIgnoreCase("female"))
            return HouseClassificationByGender.FEMALE;
        else if (houseClassificationByGender.equalsIgnoreCase("male"))
            return HouseClassificationByGender.MALE;
        return null;
    }

    public static HouseClassificationByGender scanHouseClassificationByGender(Scanner scanner) {
        printScanMsg("house classification by gender");
        HouseClassificationByGender scanHouseClassificationByGender;
        String scanHouseClassificationByGenderAsString = scanner.next();
        scanHouseClassificationByGender = stringToHouseClassificationByGender(scanHouseClassificationByGenderAsString);
        if (scanHouseClassificationByGender == null)
            printWarnMsg("house classification by gender");
        return scanHouseClassificationByGender;
    }

    public static Services scanServices(Scanner scanner) {
        boolean withElectricity = CustomizedScanners.scanBoolean("with electricity", scanner);
        boolean withWater = CustomizedScanners.scanBoolean("with water", scanner);
        boolean hasInternet = CustomizedScanners.scanBoolean("has internet", scanner);
        boolean hasTelephone = CustomizedScanners.scanBoolean("has telephone", scanner);
        boolean hasBalcony = CustomizedScanners.scanBoolean("has balcony", scanner);
        int bedroomsNum = CustomizedScanners.scanInt("bedrooms number", scanner);
        int bathroomsNum = CustomizedScanners.scanInt("bathrooms number", scanner);
        try {
            return new Services(withElectricity, withWater, hasInternet, hasTelephone, hasBalcony, bedroomsNum, bathroomsNum);
        } catch (UnacceptableValueException e) {
            if(ViewsValidation.isNegativeNumber(bedroomsNum)) {
                logger.warning("Invalid bedrooms number!");
            }
            if(ViewsValidation.isNegativeNumber(bathroomsNum)) {
                logger.warning("Invalid bathrooms number!");
            }
            return null;
        }
    }
}
