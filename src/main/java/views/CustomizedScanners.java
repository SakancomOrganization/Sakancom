package views;

import enums.HouseClassificationByGender;
import enums.UserType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.*;

public class CustomizedScanners {
    private static final Scanner scanner = new Scanner(System.in);
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

    public static int scanInt(String type) {
        printScanMsg(type);
        int scannedInt;
        while (true) {
            try {
                scannedInt = scanner.nextInt();
                break;
            } catch (InputMismatchException inputMismatchException) {
                scanner.next();
                printWarnMsg(type);
            }
        }
        return scannedInt;
    }

    public static String scanNonEmptyString(String type) {
        printScanMsg(type);
        String scanString;
        while (true) {
            scanString = scanner.nextLine();
            if (!scanString.isEmpty())
                break;
            printWarnMsg(type);
        }
        return scanString;
    }

    public static String scanString(String type) {
        printScanMsg(type);
        return scanner.nextLine();
    }

    public static boolean scanBoolean(String type) {
        printScanMsg(type);
        boolean scanBoolean;
        while (true) {
            try {
                scanBoolean = scanner.nextBoolean();
                break;
            } catch (InputMismatchException inputMismatchException) {
                scanner.next();
                printWarnMsg(type);
            }
        }
        return scanBoolean;
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

    public static UserType scanUserType() {
        printScanMsg("user type");
        UserType scanUserType;
        String scanUserTypeAsString;
        while (true) {
            scanUserTypeAsString = scanner.nextLine();
            scanUserType = stringToUserType(scanUserTypeAsString);
            if (scanUserType != null) {
                return scanUserType;
            }
            printWarnMsg("user type");
        }
    }

    public static Date scanBirthdate() {
        printScanMsg("birthdate");
        Date scanBirthdate;
        while (true) {
            try {
                String scanBirthdateAsString = scanner.nextLine();
                scanBirthdate = new SimpleDateFormat("dd/MM/yyyy").parse(scanBirthdateAsString);
                break;
            } catch (ParseException e) {
                printWarnMsg("birthdate");
            }
        }
        return scanBirthdate;
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

    public static HouseClassificationByGender scanHouseClassificationByGender() {
        printScanMsg("house classification by gender");
        HouseClassificationByGender scanHouseClassificationByGender;
        String scanHouseClassificationByGenderAsString;
        while (true) {
            scanHouseClassificationByGenderAsString = scanner.nextLine();
            scanHouseClassificationByGender = stringToHouseClassificationByGender(scanHouseClassificationByGenderAsString);
            if (scanHouseClassificationByGender != null) {
                return scanHouseClassificationByGender;
            }
            printWarnMsg("house classification by gender");
        }
    }
}