package objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Building {
    private int id;
    private String name;
    private User owner;
    private Location location;
    private List<House> houses;

    public Building(int id, String name, User owner, Location location) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.location = location;
        houses = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<House> getHouses() {
        return houses;
    }

    public void setHouses(List<House> houses) {
        this.houses = houses;
    }

    public void addHouse(House house) {
        if(!houses.contains(house))
            houses.add(house);
    }

    public void removeHouse(House house) {
        houses.remove(house);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Building building) {
            return this.id == building.getId();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}