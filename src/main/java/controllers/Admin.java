package controllers;

import enums.InfoStatus;
import exceptions.BuildingNotFoundException;
import exceptions.HouseNotFoundException;
import exceptions.UserNotFoundException;
import models.Building;
import models.House;
import models.Sakancom;
import models.User;

import java.util.ArrayList;
import java.util.List;

public class Admin {
    public static List<User> listAllUsers() {
        return Sakancom.getUsers();
    }

    public static void removeUser(String username) throws UserNotFoundException {
        Sakancom.removeUser(Sakancom.getUserByUsername(username));
    }

    public static List<Building> getAllUpdatedBuildings() {
        return Sakancom.getBuildings().stream().filter(building -> building.getInfoStatus() == InfoStatus.DIRTY).toList();
    }

    public static void acceptBuildingUpdate(int buildingId) throws BuildingNotFoundException {
        Sakancom.getBuildingById(buildingId).setInfoStatus(InfoStatus.ACCEPTED);
    }

    public static void rejectBuildingUpdates(int buildingId) throws BuildingNotFoundException {
        Sakancom.getBuildingById(buildingId).setInfoStatus(InfoStatus.REJECTED);
    }

    public static List<House> getAllUpdatedHouses() {
        List<House> updatedHouses = new ArrayList<>();
        for(Building building : Sakancom.getBuildings()) {
            updatedHouses.addAll(building.getHouses().stream().filter(house -> house.getInfoStatus() == InfoStatus.DIRTY).toList());
        }
        return updatedHouses;
    }

    public static void acceptHouseUpdate(int buildingId, int houseId) throws HouseNotFoundException, BuildingNotFoundException {
        Sakancom.getBuildingById(buildingId).getHouseById(houseId).setInfoStatus(InfoStatus.ACCEPTED);
    }

    public static void rejectHouseUpdate(int buildingId, int houseId) throws HouseNotFoundException, BuildingNotFoundException {
        Sakancom.getBuildingById(buildingId).getHouseById(houseId).setInfoStatus(InfoStatus.REJECTED);
    }
}
