package com.sakancom.source;

import java.sql.Blob;
import java.util.List;

public class House {
    private int id;
    private User owner;
    private Location location;
    private Services services;
    private int monthlyRent;
    private List<Neighbor> neighbors;
    private List<Blob> images;

    public House(int id, User owner, Location location, Services services, int monthlyRent, List<Neighbor> neighbors, List<Blob> images) {
        this.id = id;
        this.owner = owner;
        this.location = location;
        this.services = services;
        this.monthlyRent = monthlyRent;
        this.neighbors = neighbors;
        this.images = images;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Services getServices() {
        return services;
    }

    public void setServices(Services services) {
        this.services = services;
    }

    public int getMonthlyRent() {
        return monthlyRent;
    }

    public void setMonthlyRent(int monthlyRent) {
        this.monthlyRent = monthlyRent;
    }

    public List<Neighbor> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(List<Neighbor> neighbors) {
        this.neighbors = neighbors;
    }

    public List<Blob> getImages() {
        return images;
    }

    public void setImages(List<Blob> images) {
        this.images = images;
    }
}
