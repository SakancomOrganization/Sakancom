package models;

import java.util.Objects;

public class Location {
    private String city;
    private String street;

    public Location(String city, String street) {
        this.city = city;
        this.street = street;
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

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Location location) {
           return (location.getCity().isEmpty() || this.city.equalsIgnoreCase(location.getCity()))
                   && (location.getStreet().isEmpty() || this.street.equalsIgnoreCase(location.getStreet()));
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street);
    }
}
