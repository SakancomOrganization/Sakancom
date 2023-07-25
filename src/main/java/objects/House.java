package objects;

import enums.HouseClassificationByGender;
import enums.InfoStatus;
import enums.SaleStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class House {
    private int id;
    private Services services;
    private int monthlyRent;
    private List<Neighbor> neighbors;
    private List<String> images;
    private InfoStatus infoStatus;
    private SaleContract saleContract;
    private HouseRate houseRate;
    private HouseClassificationByGender houseClassificationByGender;

    public House(int id, Services services, int monthlyRent, HouseClassificationByGender houseClassificationByGender) {
        this.id = id;
        this.services = services;
        this.monthlyRent = monthlyRent;
        neighbors = new ArrayList<>();
        images  = new ArrayList<>();
        infoStatus = InfoStatus.ACCEPTED;
        saleContract = new SaleContract(null, null, SaleStatus.AVAILABLE);
        houseRate = new HouseRate();
        this.houseClassificationByGender = houseClassificationByGender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        this.neighbors.clear();
        neighbors.forEach(this::addNeighbor);
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images.clear();
        images.forEach(this::addImage);
    }

    public InfoStatus getInfoStatus() {
        return infoStatus;
    }

    public void setInfoStatus(InfoStatus infoStatus) {
        this.infoStatus = infoStatus;
    }

    public SaleContract getSaleContract() {
        return saleContract;
    }

    public void setSaleContract(SaleContract saleContract) {
        this.saleContract = saleContract;
    }

    public HouseRate getHouseRate() {
        return houseRate;
    }

    public void setHouseRate(HouseRate houseRate) {
        this.houseRate = houseRate;
    }

    public HouseClassificationByGender getHouseClassificationByGender() {
        return houseClassificationByGender;
    }

    public void setHouseClassificationByGender(HouseClassificationByGender houseClassificationByGender) {
        this.houseClassificationByGender = houseClassificationByGender;
    }

    public void addNeighbor(Neighbor neighbor) {
        if(!neighbors.contains(neighbor)) {
            neighbors.add(neighbor);
        }
    }

    public void removeNeighbor(Neighbor neighbor) {
        neighbors.remove(neighbor);
    }

    public void addImage(String image) {
        if(!images.contains(image)) {
            images.add(image);
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
        return Objects.hash(id);
    }
}
