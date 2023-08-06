package views;

import controllers.Admin;
import enums.UserType;
import exceptions.*;
import models.House;
import models.Location;
import models.Name;
import models.Sakancom;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class AdminView {
    private static final Logger logger = Logger.getLogger(AdminView.class.getName());
    public static final String BUILDING_ID = "building ID";

    private AdminView() {

    }

    public static void listAllUsersView() {
        CollectionsPrinter.printUsers(Admin.listAllUsers());
    }

    public static void listAllBuildingsView() {
        CollectionsPrinter.printBuildings(Admin.listAllBuildings());
    }

    public static void listAllBuildingsInHouseView() {
        int buildingId = CustomizedScanners.scanInt(BUILDING_ID);
        while (true) {
            try {
                CollectionsPrinter.printHouses(Admin.listAllHousesInBuilding(buildingId));
                break;
            } catch (BuildingNotFoundException e) {
                logger.warning("Invalid building ID!");
                buildingId = CustomizedScanners.scanInt(BUILDING_ID);
            }
        }
    }

    public static void searchAboutUsersView() {
        String username = CustomizedScanners.scanString("username");
        UserType userType = CustomizedScanners.scanUserType();
        String firstName = CustomizedScanners.scanString("first name");
        String middleName = CustomizedScanners.scanString("middle name");
        String lastName = CustomizedScanners.scanString("last name");
        String email = CustomizedScanners.scanString("email");
        String phoneNumber = CustomizedScanners.scanString("phone number");
        String major = CustomizedScanners.scanString("major");

        Name name = new Name(firstName, middleName, lastName);
        CollectionsPrinter.printUsers(Admin.searchAboutUsers(username,
                userType,
                name,
                email,
                phoneNumber,
                major));
    }

    public static void searchAboutBuildingsView() {
       int buildingId = CustomizedScanners.scanInt(BUILDING_ID);
       String buildingName = CustomizedScanners.scanString("building name");
       String ownerUsername = CustomizedScanners.scanString("owner username");
       String ownerFirstName = CustomizedScanners.scanString("owner first name");
       String ownerMiddleName = CustomizedScanners.scanString("owner middle name");
       String ownerLastName = CustomizedScanners.scanString("owner last name");
       String city = CustomizedScanners.scanString("city");
       String street = CustomizedScanners.scanString("street");

        Name ownerName = new Name(ownerFirstName, ownerMiddleName, ownerLastName);
        Location location = new Location(city, street);
        CollectionsPrinter.printBuildings(Admin.searchAboutBuildings(buildingId,
                buildingName,
                ownerUsername,
                ownerName,
                location));
    }

    public static void removeUserView() {
        String username = CustomizedScanners.scanNonEmptyString("username");
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
        int buildingId = CustomizedScanners.scanInt(BUILDING_ID);
        try {
            Admin.acceptBuildingUpdate(buildingId);
        } catch (BuildingNotFoundException e) {
            logger.warning("Invalid building ID!");
        }
    }

    public static void rejectBuildingUpdatesView() {
        int buildingId = CustomizedScanners.scanInt(BUILDING_ID);
        try {
            Admin.rejectBuildingUpdates(buildingId);
        } catch (BuildingNotFoundException e) {
            logger.warning("Invalid building ID!");
        }
    }

    public static void getAllUpdatesHousesView() {
        CollectionsPrinter.printHouses(Admin.getAllUpdatedHouses());
    }

    public static void acceptHousesUpdatesView() {
        int buildingId = CustomizedScanners.scanInt(BUILDING_ID);
        int houseId = CustomizedScanners.scanInt("house ID");
        try {
            Admin.acceptHouseUpdate(buildingId, houseId);
        } catch (BuildingNotFoundException e) {
            logger.warning("Invalid building ID!");
        } catch (HouseNotFoundException e) {
            logger.warning("Invalid house ID!");
        }
    }

    public static void rejectHousesUpdatesView() {
        int buildingId = CustomizedScanners.scanInt(BUILDING_ID);
        int houseId = CustomizedScanners.scanInt("house ID");
        try {
            Admin.rejectHouseUpdate(buildingId, houseId);
        } catch (BuildingNotFoundException e) {
            logger.warning("Invalid building ID!");
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
