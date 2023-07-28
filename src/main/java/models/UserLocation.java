package models;

import java.util.Objects;

public class UserLocation extends Location {
    private String building;
    private int floorNum;

    public UserLocation(String city, String street, String building, int floorNum) {
        super(city, street);
        this.building = building;
        setFloorNum(floorNum); // to check the floor number if it is less than zero
    }

    public int getFloorNum() {
        return floorNum;
    }

    public void setFloorNum(int floorNum) throws NumberFormatException {
        if(floorNum < 0)
            throw new NumberFormatException();
        this.floorNum = floorNum;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof UserLocation userLocation) {
            return super.equals(userLocation)
                    && this.getBuilding().equalsIgnoreCase(userLocation.getBuilding())
                    && this.floorNum == userLocation.getFloorNum();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), building, floorNum);
    }
}
