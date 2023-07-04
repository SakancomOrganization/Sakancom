package objects;

public class Location {
    private String city;
    private String street;
    private String building;
    private int floorNum;

    public Location() {

    }
    public Location(String city, String street, String building, int floorNum) {
        this.city = city;
        this.street = street;
        this.building = building;
        this.floorNum = floorNum;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public int getFloorNum() {
        return floorNum;
    }

    public void setFloorNum(int floorNum) {
        this.floorNum = floorNum;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Location location) {
           return (location.getCity().isEmpty() || this.city.equals(location.getCity()))
                   && (location.getStreet().isEmpty() || this.street.equals(location.getStreet()))
                   && (location.getBuilding().isEmpty() || this.building.equals(location.getBuilding()))
                   && (location.floorNum == -1 || this.floorNum == location.getFloorNum());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
