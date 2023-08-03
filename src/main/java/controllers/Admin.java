package controllers;

import enums.InfoStatus;
import enums.SaleStatus;
import enums.UserType;
import exceptions.AdminCannotBeRemovedException;
import exceptions.BuildingNotFoundException;
import exceptions.HouseNotFoundException;
import exceptions.UserNotFoundException;
import helpers.StringsComparator;
import models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return Sakancom.getUsers().stream().filter(user -> StringsComparator.compare(user.getUsername(), username)
                && (userType == null || user.getUserType().equals(userType))
                && (name == null || user.getName().equals(name))
                && StringsComparator.compare(user.getContactInfo().getEmail(), email)
                && StringsComparator.compare(user.getContactInfo().getPhoneNumber(), phoneNumber)
                && StringsComparator.compare(user.getContactInfo().getMajor(), major)).toList();
    }

    public static List<Building> searchAboutBuildings(int id, String buildingName, String ownerUsername, Name ownerName, Location location) {
        return Sakancom.getBuildings().stream().filter(building -> (id == -1 || building.getId() == id)
                && StringsComparator.compare(building.getName(), buildingName)
                && StringsComparator.compare(building.getOwner().getUsername(), ownerUsername)
                && (ownerName == null || building.getOwner().getName().equals(ownerName))
                && (location == null || building.getLocation().equals(location))).toList();
    }

    public static void removeUser(String username) throws UserNotFoundException, AdminCannotBeRemovedException {
        User user = Sakancom.getUserByUsername(username);
        if(user.getUserType() == UserType.ADMIN)
            throw new AdminCannotBeRemovedException();
        Sakancom.removeUser(user);
    }

    public static List<Building> getAllUpdatedBuildings() {
        return Sakancom.getBuildings().stream().filter(building -> building.getInfoStatus() == InfoStatus.DIRTY).toList();
    }

    public static void acceptBuildingUpdate(int buildingId) throws BuildingNotFoundException {
        setBuildingInfoStatus(buildingId, InfoStatus.ACCEPTED);
    }

    public static void rejectBuildingUpdates(int buildingId) throws BuildingNotFoundException {
        setBuildingInfoStatus(buildingId, InfoStatus.REJECTED);
    }

    private static void setBuildingInfoStatus(int buildingId, InfoStatus infoStatus) throws BuildingNotFoundException {
        Sakancom.getBuildingById(buildingId).setInfoStatus(infoStatus);
    }

    public static List<House> getAllUpdatedHouses() {
        List<House> updatedHouses = new ArrayList<>();
        for(Building building : Sakancom.getBuildings()) {
            updatedHouses.addAll(building.getHouses().stream().filter(house -> house.getInfoStatus() == InfoStatus.DIRTY).toList());
        }
        return updatedHouses;
    }

    public static void acceptHouseUpdate(int buildingId, int houseId) throws HouseNotFoundException, BuildingNotFoundException {
        setHouseInfoStatus(buildingId, houseId, InfoStatus.ACCEPTED);
    }

    public static void rejectHouseUpdate(int buildingId, int houseId) throws HouseNotFoundException, BuildingNotFoundException {
        setHouseInfoStatus(buildingId, houseId, InfoStatus.REJECTED);
    }

    private static void setHouseInfoStatus(int buildingId, int houseId, InfoStatus infoStatus) throws BuildingNotFoundException, HouseNotFoundException {
        Sakancom.getBuildingById(buildingId).getHouseById(houseId).setInfoStatus(infoStatus);
    }

    public static Map<String, Integer> getStatistics() {
        Map<String, Integer> statisticsMap = new HashMap<>();
        statisticsMap.put("adminsNum", countUserTypesNum(UserType.ADMIN));
        statisticsMap.put("ownersNum", countUserTypesNum(UserType.OWNER));
        statisticsMap.put("tenantsNum", countUserTypesNum(UserType.TENANT));
        statisticsMap.put("buildingsNum", countBuildingsNum());
        statisticsMap.put("housesNum", countHousesNum());
        statisticsMap.put("availableHousesNum", countHousesNumInStatus(SaleStatus.AVAILABLE));
        statisticsMap.put("unavailableHousesNum", countHousesNumInStatus(SaleStatus.UNAVAILABLE));
        statisticsMap.put("requestedHousesNum", countHousesNumInStatus(SaleStatus.REQUESTED));
        return statisticsMap;
    }

    private static int countUserTypesNum(UserType userType) {
        return Sakancom.getUsers().stream().filter(user -> user.getUserType() == userType).toList().size();
    }

    private static int countBuildingsNum() {
        return Sakancom.getBuildings().size();
    }

    private static int countHousesNum() {
        int housesCount = 0;
        for(Building building : Sakancom.getBuildings()) {
            housesCount += building.getHouses().size();
        }
        return housesCount;
    }

    private static int countHousesNumInStatus(SaleStatus saleStatus) {
        int availableHousesCount = 0;
        for(Building building : Sakancom.getBuildings()) {
            availableHousesCount += building.getHouses().stream().filter(house -> house.getSaleContract().getSaleStatus() == saleStatus).toList().size();
        }
        return availableHousesCount;
    }
}
