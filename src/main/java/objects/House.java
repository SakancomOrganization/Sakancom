package objects;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class House {
    private int id;
    private User owner;
    private Location location;
    private Services services;
    private int monthlyRent;
    private List<Neighbor> neighbors;
    private List<Icon> images;

    public House(int id, User owner, Location location, Services services, int monthlyRent) {
        this.id = id;
        this.owner = owner;
        this.location = location;
        this.services = services;
        this.monthlyRent = monthlyRent;
        neighbors = new ArrayList<>();
        images  = new ArrayList<>();
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

    public List<Icon> getImages() {
        return images;
    }

    public void setImages(List<Icon> images) {
        this.images = images;
    }

    public void addNeighbor(Neighbor neighbor) {
        if(!neighbors.contains(neighbor)) {
            neighbors.add(neighbor);
        }
    }

    public void removeNeighbor(Neighbor neighbor) {
        neighbors.remove(neighbor);
    }

    public void addImage(ImageIcon imageIcon) {
        if(!images.contains(imageIcon)) {
            images.add(imageIcon);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof House house) {
            return this.id == house.getId();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
