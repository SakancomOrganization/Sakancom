package views;

import controllers.Owner;
import exceptions.BuildingNotFoundException;
import exceptions.HouseNotFoundException;
import exceptions.UnacceptableValueException;

import java.util.logging.Logger;

public class OwnerView {
    public static final String FIELD = "field";
    public static final String VALUE = "value";
    public static final String BUILDING_ID = "building ID";
    public static final String HOUSE_ID = "house ID";
    public static final String INVALID_BUILD_ID = "Invalid build ID";
    private static final Logger logger = Logger.getLogger(OwnerView.class.getName());
    public static final String INVALID_HOUSE_ID = "Invalid house ID!";

    private OwnerView() {

    }

    public static void listAllOwnBuildingsView() {
        CollectionsPrinter.printBuildings(Owner.listAllOwnBuildings());
    }

    public static void listAllHousesInOwnBuildingView() {
        int buildingId = CustomizedScanners.scanInt(BUILDING_ID);
        while (true) {
            try {
                CollectionsPrinter.printHouses(Owner.listAllHousesInOwnBuilding(buildingId));
                break;
            } catch (BuildingNotFoundException e) {
                logger.warning(INVALID_BUILD_ID);
                buildingId = CustomizedScanners.scanInt(BUILDING_ID);
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
        int buildingId = CustomizedScanners.scanInt(BUILDING_ID);
        String field = CustomizedScanners.scanNonEmptyString(FIELD);
        String value = CustomizedScanners.scanNonEmptyString(VALUE);
        while (true) {
            try {
                if(ViewsValidation.isValidBuildingField(field)) {
                    Owner.updateBuildingInfo(buildingId, field, value);
                    break;
                } else {
                    logger.warning("Invalid field!");
                    field = CustomizedScanners.scanNonEmptyString(FIELD);
                }
            } catch (BuildingNotFoundException e) {
                logger.warning(INVALID_BUILD_ID);
                buildingId = CustomizedScanners.scanInt(BUILDING_ID);
            }
        }
    }

    private static void printHouseFields() {
        String outputString = "House fields: monthlyRent - includesElectricity - includesWater - hasInternet - hasTelephone - hasBalcony - bedroomsNum - bathroomsNum - houseClassificationByGender\n";
        logger.info(outputString);
    }

    public static void updateHouseInfoView() {
        printHouseFields();
        int buildingId = CustomizedScanners.scanInt(BUILDING_ID);
        int houseId = CustomizedScanners.scanInt(HOUSE_ID);
        String field = CustomizedScanners.scanNonEmptyString(FIELD);
        String value = CustomizedScanners.scanNonEmptyString(VALUE);
        while (true) {
            try {
                if(ViewsValidation.isValidHouseField(field)) {
                    Owner.updateHouseInfo(buildingId, houseId, field, value);
                    break;
                } else {
                    logger.warning("Invalid field!");
                    field = CustomizedScanners.scanNonEmptyString(FIELD);
                }
            } catch (BuildingNotFoundException e) {
                logger.warning(INVALID_BUILD_ID);
                buildingId = CustomizedScanners.scanInt(BUILDING_ID);
            } catch (HouseNotFoundException e) {
                logger.warning(INVALID_HOUSE_ID);
                buildingId = CustomizedScanners.scanInt(HOUSE_ID);
            } catch (UnacceptableValueException e) {
                logger.warning("Invalid value!");
                value = CustomizedScanners.scanNonEmptyString(VALUE);
            }
        }
    }
}
