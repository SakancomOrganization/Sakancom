package objects;

import java.util.Objects;

public class Location {
    private String city;
    private String street;
    private String building;

    public Location(String city, String street, String building) {
        this.city = city;
        this.street = street;
        this.building = building;
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

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Location location) {
           return (location.getCity().isEmpty() || this.city.equalsIgnoreCase(location.getCity()))
                   && (location.getStreet().isEmpty() || this.street.equalsIgnoreCase(location.getStreet()))
                   && (location.getBuilding().isEmpty() || this.building.equalsIgnoreCase(location.getBuilding()));
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, building);
    }
}
