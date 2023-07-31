package controllers;

import enums.InfoStatus;
import enums.UserType;
import exceptions.BuildingNotFoundException;
import exceptions.HouseNotFoundException;
import exceptions.UserNotFoundException;
import models.*;

import java.util.ArrayList;
import java.util.List;

public class Admin {
    private Admin() {

    }

    public static List<User> listAllUsers() {
        return Sakancom.getUsers();
    }

    public static List<Building> listAllBuildings() {
        return Sakancom.getBuildings();
    }

    public static List<House> listAllHousesInBuilding(int buildingId) throws BuildingNotFoundException {
        return Sakancom.getBuildingById(buildingId).getHouses();
    }

    public static List<User> searchAboutUsers(String username, UserType userType, Name name, String email, String phoneNumber, String major) {
        return Sakancom.getUsers().stream().filter(user -> (username.isEmpty() || user.getUsername().equals(username))
                && (userType == null || user.getUserType().equals(userType))
                && (name == null || user.getName().equals(name))
                && (email.isEmpty() || user.getContactInfo().getEmail().equals(email))
                && (phoneNumber.isEmpty() || user.getContactInfo().getPhoneNumber().equals(phoneNumber))
                && (major.isEmpty() || user.getContactInfo().getMajor().equalsIgnoreCase(major))).toList();
    }

    public static List<Building> searchAboutBuildings(int id, String name, User owner, Location location) {
        return Sakancom.getBuildings().stream().filter(building -> (id == -1 || building.getId() == id)
                && (name.isEmpty() || building.getName().equalsIgnoreCase(name))
                && (owner == null || building.getOwner().equals(owner))
                && (location == null || building.getLocation().equals(location))).toList();
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
