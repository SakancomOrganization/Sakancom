package controllers;

import enums.HouseClassificationByGender;
import enums.InfoStatus;
import enums.SaleStatus;
import exceptions.AlreadyFoundElementException;
import exceptions.BuildingNotFoundException;
import exceptions.HouseNotFoundException;
import exceptions.UnacceptableValueException;
import models.Building;
import models.House;
import models.Sakancom;

import java.util.ArrayList;
import java.util.List;

public class Owner {
    private Owner() {

    }

    public static List<Building> listAllOwnBuildings() {
        return Sakancom.getBuildings().stream().filter(building -> building.getOwner().equals(Sakancom.getCurrentUser())).toList();
    }

    public static List<House> listAllHousesInOwnBuilding(int buildingId) throws BuildingNotFoundException {
        Building building = Sakancom.getBuildingById(buildingId);
        if(!building.getOwner().equals(Sakancom.getCurrentUser()))
            throw new BuildingNotFoundException();
        return building.getHouses();
    }

    public static List<Building> listAllRejectedBuildings() {
        return Sakancom.getBuildings().stream().filter(building -> building.getInfoStatus() == InfoStatus.REJECTED
        && building.getOwner().equals(Sakancom.getCurrentUser())).toList();
    }

    public static List<House> listAllRejectedHouses() {
        List<House> houses = new ArrayList<>();
        for(Building building : Sakancom.getBuildings()) {
            if(building.getOwner().equals(Sakancom.getCurrentUser())) {
                houses.addAll(building.getHouses().stream().filter(house -> house.getInfoStatus() == InfoStatus.REJECTED).toList());
            }
        }
        return houses;
    }

    public static void updateBuildingInfo(int buildingId, String field, String value) throws BuildingNotFoundException {
        Building building = Sakancom.getBuildingById(buildingId);
        // building for another owner
        if(!building.getOwner().equals(Sakancom.getCurrentUser()))
            throw new BuildingNotFoundException();
        if(field.equalsIgnoreCase("name")) {
            building.setName(value);
        } else if(field.equalsIgnoreCase("city")) {
            building.getLocation().setCity(value);
        } else if(field.equalsIgnoreCase("street")) {
            building.getLocation().setStreet(value);
        }
        building.setInfoStatus(InfoStatus.DIRTY);
    }

    public static void updateHouseInfo(int buildingId, int houseId, String field, String value) throws UnacceptableValueException, HouseNotFoundException, BuildingNotFoundException {
        Building building = Sakancom.getBuildingById(buildingId);
        House house = building.getHouseById(houseId);
        // building for another owner ===> house for another owner
        if(!building.getOwner().equals(Sakancom.getCurrentUser()))
            throw new BuildingNotFoundException();
        if(field.equalsIgnoreCase("monthlyRent")) {
            house.setMonthlyRent(Integer.parseInt(value));
        } else if(field.equalsIgnoreCase("includesElectricity")) {
            house.getServices().setIncludesElectricity(Boolean.parseBoolean(value));
        } else if(field.equalsIgnoreCase("includesWater")) {
            house.getServices().setIncludesWater(Boolean.parseBoolean(value));
        } else if(field.equalsIgnoreCase("hasInternet")) {
            house.getServices().setHasInternet(Boolean.parseBoolean(value));
        } else if(field.equalsIgnoreCase("hasTelephone")) {
            house.getServices().setHasTelephone(Boolean.parseBoolean(value));
        } else if(field.equalsIgnoreCase("hasBalcony")) {
            house.getServices().setHasBalcony(Boolean.parseBoolean(value));
        } else if(field.equalsIgnoreCase("bedroomsNum")) {
            house.getServices().setBedroomsNum(Integer.parseInt(value));
        } else if(field.equalsIgnoreCase("bathroomsNum")) {
            house.getServices().setBathroomsNum(Integer.parseInt(value));
        } else if(field.equalsIgnoreCase("houseClassificationByGender")) {
            if(value.equalsIgnoreCase("Family")) {
                house.setHouseClassificationByGender(HouseClassificationByGender.FAMILY);
            } else if(value.equalsIgnoreCase("Female")) {
                house.setHouseClassificationByGender(HouseClassificationByGender.FEMALE);
            } else if(value.equalsIgnoreCase("Male")) {
                house.setHouseClassificationByGender(HouseClassificationByGender.MALE);
            }
        }
        house.setInfoStatus(InfoStatus.DIRTY);
    }

    public static void addBuilding(Building building) throws AlreadyFoundElementException {
        Sakancom.addBuilding(building);
    }

    public static void addHouse(int buildingId, House house) throws AlreadyFoundElementException, BuildingNotFoundException {
        if(!Sakancom.getBuildingById(buildingId).getOwner().equals(Sakancom.getCurrentUser()))
            throw new BuildingNotFoundException();
        Sakancom.getBuildingById(buildingId).addHouse(house);
    }

    public static void addImage(int buildingId, int houseId, String image) throws AlreadyFoundElementException, HouseNotFoundException, BuildingNotFoundException {
        if(!Sakancom.getBuildingById(buildingId).getOwner().equals(Sakancom.getCurrentUser()))
            throw new BuildingNotFoundException();
        Sakancom.getBuildingById(buildingId).getHouseById(houseId).addImage(image);
    }

    public static List<House> getAllSaleRequests() {
        List<House> houses = new ArrayList<>();
        for(Building building : Sakancom.getBuildings()) {
            if(building.getOwner().equals(Sakancom.getCurrentUser())) {
                houses.addAll(building.getHouses().stream().filter(house -> house.getSaleContract().getSaleStatus() == SaleStatus.REQUESTED).toList());
            }
        }
        return houses;
    }

    public static void acceptSaleRequest(int buildingId, int houseId) throws BuildingNotFoundException, HouseNotFoundException {
        setSaleStatus(buildingId, houseId, SaleStatus.UNAVAILABLE);
    }

    public static void breakSaleStatus(int buildingId, int houseId) throws BuildingNotFoundException, HouseNotFoundException {
        setSaleStatus(buildingId, houseId, SaleStatus.AVAILABLE);
    }

    private static void setSaleStatus(int buildingId, int houseId, SaleStatus saleStatus) throws BuildingNotFoundException, HouseNotFoundException {
        Building building = Sakancom.getBuildingById(buildingId);
        if(!building.getOwner().equals(Sakancom.getCurrentUser()))
            throw new BuildingNotFoundException();
        House house = building.getHouseById(houseId);
        house.getSaleContract().setSaleStatus(saleStatus);
    }
}
