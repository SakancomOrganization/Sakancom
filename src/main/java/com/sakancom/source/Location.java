package com.sakancom.source;

public class Location {
    private String city;
    private String street;
    private String building;
    private int floorNum;

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
}
