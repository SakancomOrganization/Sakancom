package models;

import enums.InfoStatus;
import exceptions.AlreadyFoundElementException;
import exceptions.HouseNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Building {
    private int id;
    private String name;
    private User owner;
    private Location location;
    private final List<House> houses;
    private int autoIncrementHouseId;
    private InfoStatus infoStatus;

    public Building(int id, String name, User owner, Location location) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.location = location;
        houses = new ArrayList<>();
        autoIncrementHouseId = 1;
        infoStatus = InfoStatus.DIRTY;
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

    public void setHouses(List<House> houses) throws AlreadyFoundElementException {
        this.houses.clear();
        for(House house : houses)
            addHouse(house);
    }

    public InfoStatus getInfoStatus() {
        return infoStatus;
    }

    public void setInfoStatus(InfoStatus infoStatus) {
        this.infoStatus = infoStatus;
    }

    public void addHouse(House house) throws AlreadyFoundElementException{
        if(houses.contains(house))
            throw new AlreadyFoundElementException("house");
        house.setId(autoIncrementHouseId);
        houses.add(house);
        incrementAutoIncrementId(); // increase the id for the next addition
    }

    public void removeHouse(House house) {
        houses.remove(house);
    }

    public House getHouseById(int houseId) throws HouseNotFoundException {
        List<House> resultedHouses = houses.stream().filter(house -> houseId == house.getId()).toList();
        if(resultedHouses.isEmpty())
            throw new HouseNotFoundException();

        return resultedHouses.get(0);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Building building) {
            // (id == id) or (name == name and owner == owner)
            return (this.id == building.getId())
                    || (this.name.equalsIgnoreCase(building.getName()) && this.owner.equals(building.getOwner()));
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    private void incrementAutoIncrementId() {
        autoIncrementHouseId++;
    }
}
