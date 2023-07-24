package objects;

import enums.UserType;

import java.util.ArrayList;
import java.util.List;

public class Sakancom {
    private List<User> users;
    private List<Building> buildings;

    public Sakancom () {
        users = new ArrayList<>();
        buildings  = new ArrayList<>();
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<Building> buildings) {
        this.buildings = buildings;
    }

    public void addUser(User user) {
        if(!users.contains(user)) {
            users.add(user);
        }
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public void addBuilding(Building building) {
        if(!buildings.contains(building)) {
            buildings.add(building);
        }
    }

    public void removeHouse(Building building) {
        buildings.remove(building);
    }

    public List<Building> searchAboutBuildings(int id, String name, User owner, Location location) {
        return buildings.stream().filter(building -> (id == -1 || building.getId() == id)
                && (name.isEmpty() || building.getName().equals(name))
                && (owner == null || building.getOwner().equals(owner))
                && (location == null || building.getLocation().equals(location))).toList();
    }

    public List<User> searchAboutUsers(String username, UserType userType, Name name, String email, String phoneNumber, String major) {
        return users.stream().filter(user -> (username.isEmpty() || user.getUsername().equals(username))
                && (userType == null || user.getUserType().equals(userType))
                && (name == null || user.getName().equals(name))
                && (email.isEmpty() || user.getContactInfo().getEmail().equals(email))
                && (phoneNumber.isEmpty() || user.getContactInfo().getPhoneNumber().equals(phoneNumber))
                && (major.isEmpty() || user.getContactInfo().getMajor().equals(major))).toList();
    }
}
