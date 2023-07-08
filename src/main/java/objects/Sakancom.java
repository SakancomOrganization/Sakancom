package objects;

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

    public void remove(User user) {
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
        ArrayList<House> resultHouses = new ArrayList<>();
        for(House house : houses) {
            if((id == -1 || house.getId() == id)
                && (monthlyRent == -1 || house.getMonthlyRent() == monthlyRent)
                && (location == null || house.getLocation().equals(location))
                && (services == null || house.getServices().equals(services))
                && (houseRate == -1 || house.getHouseRate().getRate() >= houseRate))
                resultHouses.add(house);
        }
        return resultHouses;
    }
}
