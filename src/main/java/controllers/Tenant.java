package controllers;

import enums.SaleStatus;
import exceptions.BuildingNotFoundException;
import exceptions.HouseNotFoundException;
import models.Building;
import models.House;
import models.Sakancom;

import java.util.ArrayList;
import java.util.List;

public class Tenant {
    public static List<House> listAvailableHouses() {
        List<House> availableHouses = new ArrayList<>();
        for(Building building : Sakancom.getBuildings()) {
            availableHouses.addAll(building.getHouses().stream().filter(house -> house.getSaleContract().getSaleStatus() == SaleStatus.AVAILABLE).toList());
        }
        return availableHouses;
    }

    public static void requestHouse(int buildingId, int houseId) throws HouseNotFoundException, BuildingNotFoundException {
        House house = Sakancom.getBuildingById(buildingId).getHouseById(houseId);
        house.getSaleContract().setSaleStatus(SaleStatus.REQUESTED);
        house.getSaleContract().setTenant(Sakancom.getCurrentUser());
    }
}
