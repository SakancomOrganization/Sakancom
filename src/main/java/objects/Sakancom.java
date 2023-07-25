package objects;

import enums.UserType;

import java.util.ArrayList;
import java.util.List;

public class Sakancom {
    private static final List<User> users = new ArrayList<>();
    private static final List<Building> buildings = new ArrayList<>();
    private static int autoIncrementBuildingId = 1;

    public static List<User> getUsers() {
        return users;
    }

    public static void setUsers(List<User> users) {
        Sakancom.users.clear();
        users.forEach(Sakancom::addUser);
    }

    public static List<Building> getBuildings() {
        return buildings;
    }

    public static void setBuildings(List<Building> buildings) {
        Sakancom.buildings.clear();
        buildings.forEach(Sakancom::addBuilding);
    }

    public static void addUser(User user) {
        if(!users.contains(user)) {
            users.add(user);
        }
    }

    public static void removeUser(User user) {
        users.remove(user);
    }

    public static void addBuilding(Building building) {
        if(!buildings.contains(building)) {
            building.setId(autoIncrementBuildingId);
            buildings.add(building);
            incrementAutoIncrementBuildingId(); // increment id for the next addition
        }
    }

    public static void removeBuilding(Building building) {
        buildings.remove(building);
    }

    public static List<User> searchAboutUsers(String username, UserType userType, Name name, String email, String phoneNumber, String major) {
        return users.stream().filter(user -> (username.isEmpty() || user.getUsername().equals(username))
                && (userType == null || user.getUserType().equals(userType))
                && (name == null || user.getName().equals(name))
                && (email.isEmpty() || user.getContactInfo().getEmail().equals(email))
                && (phoneNumber.isEmpty() || user.getContactInfo().getPhoneNumber().equals(phoneNumber))
                && (major.isEmpty() || user.getContactInfo().getMajor().equals(major))).toList();
    }

//    public static List<Building> searchAboutBuildings(int id, String name, User owner, Location location) {
//        return buildings.stream().filter(building -> (id == -1 || building.getId() == id)
//                && (name.isEmpty() || building.getName().equals(name))
//                && (owner == null || building.getOwner().equals(owner))
//                && (location == null || building.getLocation().equals(location))).toList();
//    }

    private static void incrementAutoIncrementBuildingId() {
        autoIncrementBuildingId++;
    }
}
