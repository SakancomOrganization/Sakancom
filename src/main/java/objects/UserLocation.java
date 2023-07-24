package objects;

import java.util.Objects;

public class UserLocation extends Location {
    private int floorNum;

    public UserLocation(String city, String street, String building, int floorNum) {
        super(city, street, building);
        this.floorNum = floorNum;
    }

    public int getFloorNum() {
        return floorNum;
    }

    public void setFloorNum(int floorNum) {
        this.floorNum = floorNum;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof UserLocation userLocation) {
            return super.equals(userLocation)
                    && this.floorNum == userLocation.getFloorNum();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), floorNum);
    }
}
