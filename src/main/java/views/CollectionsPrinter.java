package views;

import enums.SaleStatus;
import exceptions.AlreadyFoundElementException;
import exceptions.InvalidEmailFormatException;
import exceptions.UnacceptableValueException;
import models.Building;
import models.House;
import models.Sakancom;
import models.User;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class CollectionsPrinter {
    private static final Logger logger = Logger.getLogger(CollectionsPrinter.class.getName());
    private CollectionsPrinter() {

    }

    public static void appendHorizontalLine(StringBuilder stringBuilder, int count) {
        stringBuilder.append("\n");
        stringBuilder.append("-".repeat(count));
    }

    private static void appendUserInfo(User user, StringBuilder outputString) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        outputString.append(String.format("%n|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%-25s|%-25s|%-15s|%-32s|%-15s|%-15s|%-25s|", user.getUsername(),
                user.getName().getFirstName(), user.getName().getMiddleName(), user.getName().getLastName(),
                user.getUserType(),
                user.getUserLocation().getCity(), user.getUserLocation().getStreet(), user.getUserLocation().getBuilding(), user.getUserLocation().getFloorNum(),
                user.getContactInfo().getEmail(), user.getContactInfo().getPhoneNumber(), dateFormat.format(user.getContactInfo().getBirthdate()), user.getContactInfo().getMajor()));
    }

    public static void printUsers(List<User> users) {
        if(users.isEmpty())
            logger.info("No users!");

        final int LINE_WIDTH = 256;
        StringBuilder outputString = new StringBuilder();
        appendHorizontalLine(outputString, LINE_WIDTH);
        outputString.append(String.format("%n|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%-25s|%-25s|%-15s|%-32s|%-15s|%-15s|%-25s|",
                "Username", // username
                "First Name", "Middle Name", "Last Name", // name
                "User Type", // user type
                "City","Street","Building","Floor Number", // user location
                "Email","Phone Number","Birthdate","Major"));

        appendHorizontalLine(outputString, LINE_WIDTH);
        for(User user : users) {
            appendUserInfo(user, outputString);
        }
        appendHorizontalLine(outputString, LINE_WIDTH);
        String result = String.valueOf(outputString);
        logger.info(result);
    }

    private static void appendBuildingInfo(Building building, StringBuilder outputString) {
        outputString.append(String.format("%n|%-15s|%-15s|%-25s|%-15s|%-15s|%-15s|",
                building.getId(),
                building.getName(),
                building.getOwner().getName().getFirstName() + " " + building.getOwner().getName().getLastName(),
                building.getLocation().getCity(), building.getLocation().getStreet(),
                building.getInfoStatus()));
    }

    public static void printBuildings(List<Building> buildings) {
        if(buildings.isEmpty())
            logger.info("No buildings!");

        final int LINE_WIDTH = 107;
        StringBuilder outputString = new StringBuilder();
        appendHorizontalLine(outputString, LINE_WIDTH);
        outputString.append(String.format("%n|%-15s|%-15s|%-25s|%-15s|%-15s|%-15s|",
                "ID",
                "Name",
                "Owner",
                "City",
                "Street",
                "Info Status"));
        appendHorizontalLine(outputString, LINE_WIDTH);
        for(Building building : buildings) {
            appendBuildingInfo(building, outputString);
        }
        appendHorizontalLine(outputString, LINE_WIDTH);
        String result = String.valueOf(outputString);
        logger.info(result);
    }

    private static void appendHouseImages(House house, StringBuilder outputString) {
        outputString.append("Images:\n");
        for(String img : house.getImages())
            outputString.append(img).append("\n");
    }

    private static void appendHouseInfo(House house, StringBuilder outputString) {
        outputString.append("\nID: ").append(house.getId()).append("\n");
        outputString.append("Monthly Rent: ").append(house.getMonthlyRent()).append("\n");
        outputString.append("Includes Electricity: ").append(house.getServices().isIncludesElectricity()).append("\n");
        outputString.append("Includes Water: ").append(house.getServices().isIncludesWater()).append("\n");
        outputString.append("Has Internet: ").append(house.getServices().isHasInternet()).append("\n");
        outputString.append("Has Telephone: ").append(house.getServices().isHasTelephone()).append("\n");
        outputString.append("Has Balcony: ").append(house.getServices().isHasBalcony()).append("\n");
        outputString.append("Floor Num: ").append(house.getFloorNum()).append("\n");
        outputString.append("Info Status: ").append(house.getInfoStatus()).append("\n");
        outputString.append("House Rate: ").append(house.getHouseRate().getTotalRate()).append("\n");
        appendHouseImages(house, outputString);
        SaleStatus saleStatus = house.getSaleContract().getSaleStatus();
        outputString.append("Sale Status: ").append(saleStatus).append("\n");
        if(saleStatus == SaleStatus.UNAVAILABLE)
            outputString.append("Tenant: ").append(house.getSaleContract().getTenant().getName().getFirstName()).append(" ")
                    .append(house.getSaleContract().getTenant().getName().getLastName()).append("\n");

        outputString.append("House Classification By Gender: ").append(house.getHouseClassificationByGender()).append("\n\n");
    }

    public static void printHouses(List<House> houses) {
        if(houses.isEmpty())
            logger.info("No houses!");

        StringBuilder outputString = new StringBuilder();
        for(House house : houses) {
            appendHouseInfo(house, outputString);
        }
        String result = String.valueOf(outputString);
        logger.info(result);
    }

    public static void printMap(Map<Integer, List<House>> map) {
        StringBuilder outputString = new StringBuilder();
        for(Map.Entry<Integer, List<House>> entry : map.entrySet()) {
            outputString.append("Building ").append(entry.getKey()).append("\n");
            for(House house : entry.getValue()) {
                appendHouseInfo(house, outputString);
            }
        }
        String result = String.valueOf(outputString);
        logger.info(result);
    }
}
