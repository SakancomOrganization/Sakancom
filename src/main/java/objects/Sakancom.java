package objects;

import enums.UserType;

import java.util.ArrayList;
import java.util.List;

public class Sakancom {
    private List<User> users;
    private List<House> houses;

    public Sakancom () {
        users = new ArrayList<>();
        houses  = new ArrayList<>();
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<House> getHouses() {
        return houses;
    }

    public void setHouses(List<House> houses) {
        this.houses = houses;
    }

    public void addUser(User user) {
        if(!users.contains(user)) {
            users.add(user);
        }
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public void addHouse(House house) {
        if(!houses.contains(house)) {
            houses.add(house);
        }
    }

    public void removeHouse(House house) {
        houses.remove(house);
    }

    public List<House> searchAboutHouses(int id, int monthlyRent, Location location, Services services, double houseRate) {
        return houses.stream().filter(house -> (id == -1 || house.getId() == id)
                && (monthlyRent == -1 || house.getMonthlyRent() == monthlyRent)
                && (location == null || house.getLocation().equals(location))
                && (services == null || house.getServices().equals(services))
                && (houseRate == -1 || house.getHouseRate().getRate() >= houseRate)).toList();
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
