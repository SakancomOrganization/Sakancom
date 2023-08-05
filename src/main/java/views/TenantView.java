package views;

import controllers.Tenant;
import exceptions.BuildingNotFoundException;
import exceptions.HouseNotFoundException;

import java.util.logging.Logger;

public class TenantView {
    private static final Logger logger = Logger.getLogger(TenantView.class.getName());
    private TenantView() {

    }

    public static void listAvailableHousesView() {
        CollectionsPrinter.printMap(Tenant.listAvailableHouses());
    }

    public static void listTenantHouses() {
        CollectionsPrinter.printMap(Tenant.listTenantHouses());
    }

    public static void requestHouseView() {
        int buildingId = CustomizedScanners.scanInt("requested building ID");
        int houseId = CustomizedScanners.scanInt("requested house ID");
        while(true) {
            try {
                Tenant.requestHouse(buildingId, houseId);
                break;
            } catch (BuildingNotFoundException e) {
                buildingId = CustomizedScanners.scanInt("requested building ID");
                logger.warning("Invalid requested building ID!");
            } catch (HouseNotFoundException e) {
                houseId = CustomizedScanners.scanInt("requested house ID");
                logger.warning("Invalid requested house ID!");
            }
        }
    }

    public static void leaveHouseView() {
        int buildingId = CustomizedScanners.scanInt("left building ID");
        int houseId = CustomizedScanners.scanInt("left house ID");
        while(true) {
            try {
                Tenant.leaveHouse(buildingId, houseId);
                break;
            } catch (BuildingNotFoundException e) {
                buildingId = CustomizedScanners.scanInt("left building ID");
                logger.warning("Invalid left building ID!");
            } catch (HouseNotFoundException e) {
                houseId = CustomizedScanners.scanInt("left house ID");
                logger.warning("Invalid left house ID!");
            }
        }
    }

    public static void getAllNeighborsView() {
        int buildingId = CustomizedScanners.scanInt("building ID");
        int houseId = CustomizedScanners.scanInt("house ID");
        while(true) {
            try {
                CollectionsPrinter.printUsers(Tenant.getAllHouseNeighbors(buildingId,houseId));
                break;
            } catch (BuildingNotFoundException e) {
                buildingId = CustomizedScanners.scanInt("building ID");
                logger.warning("Invalid building ID!");
            } catch (HouseNotFoundException e) {
                houseId = CustomizedScanners.scanInt("house ID");
                logger.warning("Invalid house ID!");
            }
        }
    }
}
