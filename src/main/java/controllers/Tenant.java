package controllers;

import enums.SaleStatus;
import exceptions.BuildingNotFoundException;
import exceptions.HouseNotFoundException;
import models.Building;
import models.House;
import models.Sakancom;
import models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tenant {
    private Tenant() {

    }

    public static Map<Integer, List<House>> listAvailableHouses() {
        Map<Integer, List<House>> availableHouses = new HashMap<>();
        for(Building building : Sakancom.getBuildings()) {
            List<House> subList = new ArrayList<>();
            for (House house : building.getHouses()) {
                if(house.getSaleContract().getSaleStatus() == SaleStatus.AVAILABLE)
                    subList.add(house);
            }
            if(!subList.isEmpty())
                availableHouses.put(building.getId(), subList);
        }
        return availableHouses;
    }

    public static void requestHouse(int buildingId, int houseId) throws HouseNotFoundException, BuildingNotFoundException {
        House house = Sakancom.getBuildingById(buildingId).getHouseById(houseId);
        house.getSaleContract().setSaleStatus(SaleStatus.REQUESTED);
        house.getSaleContract().setTenant(Sakancom.getCurrentUser());
    }

    public static List<User> getAllHouseNeighbors(int buildingId, int houseId) throws BuildingNotFoundException, HouseNotFoundException {
        Building building = Sakancom.getBuildingById(buildingId);
        // floor num == floor num && id != id (expect the house itself!)
        House house = building.getHouseById(houseId);
        List<House> neighborHouses = building.getHouses().stream().filter(houseIterator -> houseIterator.getFloorNum() == house.getFloorNum()
        && (houseIterator.getId() != houseId)).toList();
        List<User> neighbors = new ArrayList<>();
        for(House houseIterator : neighborHouses) {
            neighbors.add(houseIterator.getSaleContract().getTenant());
        }
        return neighbors;
    }

    public static Map<Integer, List<House>> listTenantHouses() {
        Map<Integer, List<House>> tenantHouses = new HashMap<>();
        for(Building building : Sakancom.getBuildings()) {
            List<House> subList = new ArrayList<>();
            for (House house : building.getHouses()) {
                if(house.getSaleContract().getSaleStatus() == SaleStatus.UNAVAILABLE
                && house.getSaleContract().getTenant().equals(Sakancom.getCurrentUser()))
                    subList.add(house);
            }
            if(!subList.isEmpty())
                tenantHouses.put(building.getId(), subList);
        }
        return tenantHouses;
    }

    public static void leaveHouse(int buildingId, int houseId) throws BuildingNotFoundException, HouseNotFoundException {
        Building building = Sakancom.getBuildingById(buildingId);
        House house = building.getHouseById(houseId);
        if(!house.getSaleContract().getTenant().equals(Sakancom.getCurrentUser()))
            throw new HouseNotFoundException();
        house.getSaleContract().setSaleStatus(SaleStatus.AVAILABLE);
    }
}
