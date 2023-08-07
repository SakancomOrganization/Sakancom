package views;

import controllers.Tenant;
import exceptions.BuildingNotFoundException;
import exceptions.HouseNotFoundException;
import printers.CollectionsPrinter;
import scanners.CustomizedScanners;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
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
        int buildingId = CustomizedScanners.scanInt("requested building ID", new Scanner(System.in));
        int houseId = CustomizedScanners.scanInt("requested house ID", new Scanner(System.in));
        while(true) {
            try {
                Tenant.requestHouse(buildingId, houseId);
                break;
            } catch (BuildingNotFoundException e) {
                logger.warning("Invalid requested building ID!");
                buildingId = CustomizedScanners.scanInt("requested building ID", new Scanner(System.in));
            } catch (HouseNotFoundException e) {
                logger.warning("Invalid requested house ID!");
                houseId = CustomizedScanners.scanInt("requested house ID", new Scanner(System.in));
            }
        }
    }

    public static void leaveHouseView() {
        int buildingId = CustomizedScanners.scanInt("left building ID", new Scanner(System.in));
        int houseId = CustomizedScanners.scanInt("left house ID", new Scanner(System.in));
        while(true) {
            try {
                Tenant.leaveHouse(buildingId, houseId);
                break;
            } catch (BuildingNotFoundException e) {
                logger.warning("Invalid left building ID!");
                buildingId = CustomizedScanners.scanInt("left building ID", new Scanner(System.in));
            } catch (HouseNotFoundException e) {
                logger.warning("Invalid left house ID!");
                houseId = CustomizedScanners.scanInt("left house ID", new Scanner(System.in));
            }
        }
    }

    public static void getAllNeighborsView() {
        int buildingId = CustomizedScanners.scanInt("building ID", new Scanner(System.in));
        int houseId = CustomizedScanners.scanInt("house ID", new Scanner(System.in));
        while(true) {
            try {
                CollectionsPrinter.printUsers(Tenant.getAllHouseNeighbors(buildingId,houseId));
                break;
            } catch (BuildingNotFoundException e) {
                logger.warning("Invalid building ID!");
                buildingId = CustomizedScanners.scanInt("building ID", new Scanner(System.in));
            } catch (HouseNotFoundException e) {
                logger.warning("Invalid house ID!");
                houseId = CustomizedScanners.scanInt("house ID", new Scanner(System.in));
            }
        }
    }

    public static void seeImagesView(int buildingId, int houseId) {
        try {
            String path = "C:\\Users\\HITECH\\IdeaProjects\\Sakancom\\src\\main\\resources\\images/building_" + buildingId + "/house_" + houseId;
            Desktop desktop = Desktop.getDesktop();
            desktop.open(new File(path));
        } catch (IOException e) {
            logger.warning("Invalid path!");
        }
    }
}
