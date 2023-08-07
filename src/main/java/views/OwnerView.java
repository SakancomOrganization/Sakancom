package views;

import controllers.Owner;
import enums.HouseClassificationByGender;
import exceptions.*;
import io.FileSystemMaker;
import io.YmlHandler;
import models.*;
import printers.CollectionsPrinter;
import scanners.CustomizedScanners;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

public class OwnerView {
    public static final String FIELD = "field";
    public static final String VALUE = "value";
    public static final String BUILDING_ID = "building ID";
    public static final String HOUSE_ID = "house ID";
    public static final String INVALID_BUILD_ID = "Invalid build ID";
    public static final String INVALID_HOUSE_ID = "Invalid house ID!";
    private static final Logger logger = Logger.getLogger(OwnerView.class.getName());

    private OwnerView() {

    }

    public static void listAllOwnBuildingsView() {
        CollectionsPrinter.printBuildings(Owner.listAllOwnBuildings());
    }

    public static void listAllHousesInOwnBuildingView() {
        int buildingId = CustomizedScanners.scanInt(BUILDING_ID, new Scanner(System.in));
        while (true) {
            try {
                CollectionsPrinter.printHouses(Owner.listAllHousesInOwnBuilding(buildingId));
                break;
            } catch (BuildingNotFoundException e) {
                logger.warning(INVALID_BUILD_ID);
                buildingId = CustomizedScanners.scanInt(BUILDING_ID, new Scanner(System.in));
            }
        }
    }

    public static void listAllRejectBuildingsView() {
        CollectionsPrinter.printBuildings(Owner.listAllRejectedBuildings());
    }

    public static void listAllRejectedHousesView() {
        CollectionsPrinter.printHouses(Owner.listAllRejectedHouses());
    }

    private static void printBuildingFields() {
        String outputString = "Buildings field: name - city - street\n";
        logger.info(outputString);
    }

    public static void updateBuildingInfoView() {
        printBuildingFields();
        int buildingId = CustomizedScanners.scanInt(BUILDING_ID, new Scanner(System.in));
        String field = CustomizedScanners.scanNonEmptyString(FIELD, new Scanner(System.in));
        String value = CustomizedScanners.scanNonEmptyString(VALUE, new Scanner(System.in));
        while (true) {
            try {
                if(ViewsValidation.isValidBuildingField(field)) {
                    Owner.updateBuildingInfo(buildingId, field, value);
                    break;
                } else {
                    logger.warning("Invalid field!");
                    field = CustomizedScanners.scanNonEmptyString(FIELD, new Scanner(System.in));
                }
            } catch (BuildingNotFoundException e) {
                logger.warning(INVALID_BUILD_ID);
                buildingId = CustomizedScanners.scanInt(BUILDING_ID, new Scanner(System.in));
            }
        }
    }

    private static void printHouseFields() {
        String outputString = "House fields: monthlyRent - includesElectricity - includesWater - hasInternet - hasTelephone - hasBalcony - bedroomsNum - bathroomsNum - houseClassificationByGender\n";
        logger.info(outputString);
    }

    public static void updateHouseInfoView() {
        printHouseFields();
        int buildingId = CustomizedScanners.scanInt(BUILDING_ID, new Scanner(System.in));
        int houseId = CustomizedScanners.scanInt(HOUSE_ID, new Scanner(System.in));
        String field = CustomizedScanners.scanNonEmptyString(FIELD, new Scanner(System.in));
        String value = CustomizedScanners.scanNonEmptyString(VALUE, new Scanner(System.in));
        while (true) {
            try {
                if(ViewsValidation.isValidHouseField(field)) {
                    Owner.updateHouseInfo(buildingId, houseId, field, value);
                    break;
                } else {
                    logger.warning("Invalid field!");
                    field = CustomizedScanners.scanNonEmptyString(FIELD, new Scanner(System.in));
                }
            } catch (BuildingNotFoundException e) {
                logger.warning(INVALID_BUILD_ID);
                buildingId = CustomizedScanners.scanInt(BUILDING_ID, new Scanner(System.in));
            } catch (HouseNotFoundException e) {
                logger.warning(INVALID_HOUSE_ID);
                buildingId = CustomizedScanners.scanInt(HOUSE_ID, new Scanner(System.in));
            } catch (UnacceptableValueException e) {
                logger.warning("Invalid value!");
                value = CustomizedScanners.scanNonEmptyString(VALUE, new Scanner(System.in));
            }
        }
    }

    public static void addBuildingView() throws FileNotFoundException {
        String name = CustomizedScanners.scanNonEmptyString("building name", new Scanner(System.in));
        String city = CustomizedScanners.scanNonEmptyString("city", new Scanner(System.in));
        String street = CustomizedScanners.scanNonEmptyString("street", new Scanner(System.in));
        try {
            Location location = new Location(city, street);
            Building building = new Building(-1, name, Sakancom.getCurrentUser(), location);
            Owner.addBuilding(building);
            File file = new File(YmlHandler.getValue("path") + building.getId());
            FileSystemMaker fileSystemMaker = new FileSystemMaker();
            fileSystemMaker.mkDir(file);
        } catch (AlreadyFoundElementException e) {
            logger.warning("This building is already found! please try again!");
        }
    }

    public static void addHouseView() throws FileNotFoundException {
        int buildingId = CustomizedScanners.scanInt(BUILDING_ID, new Scanner(System.in));
        Services services = CustomizedScanners.scanServices(new Scanner(System.in));
        int monthlyRent = CustomizedScanners.scanInt("monthly rent", new Scanner(System.in));
        int floorNum = CustomizedScanners.scanInt("floor number", new Scanner(System.in));
        HouseClassificationByGender houseClassificationByGender = CustomizedScanners.scanHouseClassificationByGender(new Scanner(System.in));
        try {
            House house = new House(-1, services, monthlyRent, floorNum, houseClassificationByGender);
            Owner.addHouse(buildingId, house);
            File file = new File(YmlHandler.getValue("path") +buildingId + "\\house_" + house.getId());
            FileSystemMaker fileSystemMaker = new FileSystemMaker();
            fileSystemMaker.mkDir(file);
        } catch (AlreadyFoundElementException e) {
            logger.warning("This house is already found! please try again!");
        } catch (BuildingNotFoundException e) {
            logger.warning(INVALID_BUILD_ID);
        } catch (UnacceptableValueException e) {
            if(ViewsValidation.isNegativeNumber(monthlyRent)) {
                logger.warning("Invalid monthly rent!");
            }
            if(ViewsValidation.isNegativeNumber(floorNum)) {
                logger.warning("Invalid floor number!");
            }
        }
    }

    public static void addImageView() throws FileNotFoundException {
        int buildingId = CustomizedScanners.scanInt(BUILDING_ID, new Scanner(System.in));
        int houseId = CustomizedScanners.scanInt(HOUSE_ID, new Scanner(System.in));
        String image = CustomizedScanners.scanNonEmptyString("image", new Scanner(System.in));
        try {
            Owner.addImage(buildingId, houseId, image);
            File file = new File(YmlHandler.getValue("path") +buildingId + "\\house_" + houseId + File.separator + image);
            FileSystemMaker fileSystemMaker = new FileSystemMaker();
            if(fileSystemMaker.mkFile(file))
                logger.info("Image is added successfully!");
        } catch (AlreadyFoundElementException e) {
            logger.warning("This image is already found!");
        } catch (BuildingNotFoundException e) {
            logger.warning(INVALID_BUILD_ID);
        } catch (HouseNotFoundException e) {
            logger.warning(INVALID_HOUSE_ID);
        } catch (IOException e) {
            logger.warning("Invalid file name!");
        }
    }

    public static void getAllSaleRequestsView() {
        CollectionsPrinter.printHouses(Owner.getAllSaleRequests());
    }

    public static void acceptSaleRequestView() {
        int buildingId = CustomizedScanners.scanInt(BUILDING_ID, new Scanner(System.in));
        int houseId = CustomizedScanners.scanInt(HOUSE_ID, new Scanner(System.in));
        try {
            Owner.acceptSaleRequest(buildingId, houseId);
        } catch (BuildingNotFoundException e) {
            logger.warning(INVALID_BUILD_ID);
        } catch (HouseNotFoundException e) {
            logger.warning(INVALID_HOUSE_ID);
        }
    }

    public static void breakSaleStatusView() {
        int buildingId = CustomizedScanners.scanInt(BUILDING_ID, new Scanner(System.in));
        int houseId = CustomizedScanners.scanInt(HOUSE_ID, new Scanner(System.in));
        try {
            Owner.breakSaleStatus(buildingId, houseId);
        } catch (BuildingNotFoundException e) {
            logger.warning(INVALID_BUILD_ID);
        } catch (HouseNotFoundException e) {
            logger.warning(INVALID_HOUSE_ID);
        }
    }
}
