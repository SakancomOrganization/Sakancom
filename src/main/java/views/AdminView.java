package views;

import controllers.Admin;
import enums.UserType;
import exceptions.*;
import models.Location;
import models.Name;
import printers.CollectionsPrinter;
import scanners.CustomizedScanners;

import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

public class AdminView {
    private static final Logger logger = Logger.getLogger(AdminView.class.getName());
    public static final String BUILDING_ID = "building ID";
    public static final String INVALID_BUILDING_ID = "Invalid building ID!";

    private AdminView() {

    }

    public static void listAllUsersView() {
        CollectionsPrinter.printUsers(Admin.listAllUsers());
    }

    public static void listAllBuildingsView() {
        CollectionsPrinter.printBuildings(Admin.listAllBuildings());
    }

    public static void listAllBuildingsInHouseView() {
        int buildingId = CustomizedScanners.scanInt(BUILDING_ID, new Scanner(System.in));
        while (true) {
            try {
                CollectionsPrinter.printHouses(Admin.listAllHousesInBuilding(buildingId));
                break;
            } catch (BuildingNotFoundException e) {
                logger.warning(INVALID_BUILDING_ID);
                buildingId = CustomizedScanners.scanInt(BUILDING_ID, new Scanner(System.in));
            }
        }
    }

    public static void searchAboutUsersView() {
        String username = CustomizedScanners.scanString("username", new Scanner(System.in));
        UserType userType = CustomizedScanners.scanUserType(new Scanner(System.in));
        String firstName = CustomizedScanners.scanString("first name", new Scanner(System.in));
        String middleName = CustomizedScanners.scanString("middle name", new Scanner(System.in));
        String lastName = CustomizedScanners.scanString("last name", new Scanner(System.in));
        String email = CustomizedScanners.scanString("email", new Scanner(System.in));
        String phoneNumber = CustomizedScanners.scanString("phone number", new Scanner(System.in));
        String major = CustomizedScanners.scanString("major", new Scanner(System.in));

        Name name = new Name(firstName, middleName, lastName);
        CollectionsPrinter.printUsers(Admin.searchAboutUsers(username,
                userType,
                name,
                email,
                phoneNumber,
                major));
    }

    public static void searchAboutBuildingsView() {
       int buildingId = CustomizedScanners.scanInt(BUILDING_ID, new Scanner(System.in));
       String buildingName = CustomizedScanners.scanString("building name", new Scanner(System.in));
       String ownerUsername = CustomizedScanners.scanString("owner username", new Scanner(System.in));
       String ownerFirstName = CustomizedScanners.scanString("owner first name", new Scanner(System.in));
       String ownerMiddleName = CustomizedScanners.scanString("owner middle name", new Scanner(System.in));
       String ownerLastName = CustomizedScanners.scanString("owner last name", new Scanner(System.in));
       String city = CustomizedScanners.scanString("city", new Scanner(System.in));
       String street = CustomizedScanners.scanString("street", new Scanner(System.in));

        Name ownerName = new Name(ownerFirstName, ownerMiddleName, ownerLastName);
        Location location = new Location(city, street);
        CollectionsPrinter.printBuildings(Admin.searchAboutBuildings(buildingId,
                buildingName,
                ownerUsername,
                ownerName,
                location));
    }

    public static void removeUserView() {
        String username = CustomizedScanners.scanNonEmptyString("username", new Scanner(System.in));
        try {
            Admin.removeUser(username);
        } catch (UserNotFoundException e) {
            logger.warning("Invalid username!");
        } catch (AdminCannotBeRemovedException e) {
            logger.warning("You cannot remove the admin!");
        }
    }

    public static void getAllUpdatedBuildingsView() {
        CollectionsPrinter.printBuildings(Admin.getAllUpdatedBuildings());
    }

    public static void acceptBuildingUpdatesView() {
        int buildingId = CustomizedScanners.scanInt(BUILDING_ID, new Scanner(System.in));
        try {
            Admin.acceptBuildingUpdate(buildingId);
        } catch (BuildingNotFoundException e) {
            logger.warning(INVALID_BUILDING_ID);
        }
    }

    public static void rejectBuildingUpdatesView() {
        int buildingId = CustomizedScanners.scanInt(BUILDING_ID, new Scanner(System.in));
        try {
            Admin.rejectBuildingUpdates(buildingId);
        } catch (BuildingNotFoundException e) {
            logger.warning(INVALID_BUILDING_ID);
        }
    }

    public static void getAllUpdatesHousesView() {
        CollectionsPrinter.printHouses(Admin.getAllUpdatedHouses());
    }

    public static void acceptHousesUpdatesView() {
        int buildingId = CustomizedScanners.scanInt(BUILDING_ID, new Scanner(System.in));
        int houseId = CustomizedScanners.scanInt("house ID", new Scanner(System.in));
        try {
            Admin.acceptHouseUpdate(buildingId, houseId);
        } catch (BuildingNotFoundException e) {
            logger.warning(INVALID_BUILDING_ID);
        } catch (HouseNotFoundException e) {
            logger.warning("Invalid house ID!");
        }
    }

    public static void rejectHousesUpdatesView() {
        int buildingId = CustomizedScanners.scanInt(BUILDING_ID, new Scanner(System.in));
        int houseId = CustomizedScanners.scanInt("house ID", new Scanner(System.in));
        try {
            Admin.rejectHouseUpdate(buildingId, houseId);
        } catch (BuildingNotFoundException e) {
            logger.warning(INVALID_BUILDING_ID);
        } catch (HouseNotFoundException e) {
            logger.warning("Invalid house ID!");
        }
    }

    public static void getStatisticsView() {
        final int LINE_WIDTH = 34;
        StringBuilder outputString = new StringBuilder();
        CollectionsPrinter.appendHorizontalLine(outputString, LINE_WIDTH);
        outputString.append(String.format("%n|%-25s|%-6s|","Statistics","Result"));
        CollectionsPrinter.appendHorizontalLine(outputString, LINE_WIDTH);
        Map<String, Integer> statisticsMap = Admin.getStatistics();
        for(Map.Entry<String, Integer> entry : statisticsMap.entrySet()) {
            outputString.append(String.format("%n|%-25s|%-6s|",entry.getKey(), entry.getValue()));
        }
        CollectionsPrinter.appendHorizontalLine(outputString, LINE_WIDTH);
        String result = String.valueOf(outputString);
        logger.info(result);
    }
}
