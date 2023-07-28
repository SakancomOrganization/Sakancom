package models;

import enums.HouseClassificationByGender;
import enums.InfoStatus;
import enums.SaleStatus;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestHouse {
    private House house;
    private Services services;
    private Neighbor neighbor;

    @Before
    public void setup() {
        neighbor = new Neighbor("Anas", "A very quiet neighbor");
        house = new House(1, null, 400, 1, HouseClassificationByGender.FAMILY);
    }

    @Test
    public void testId() {
        house.setId(2);
        assertEquals(2, house.getId());
    }

    @Test
    public void testServices() {
        services = new Services(true, true, true, true, true, 4, 2);
        house.setServices(services);
        assertEquals(services, house.getServices());
    }

    @Test
    public void testMonthlyRent() {
        house.setMonthlyRent(200);
        assertEquals(200, house.getMonthlyRent());
        assertThrows(NumberFormatException.class, () -> {
           house.setMonthlyRent(-100);
        });
    }

    @Test
    public void testFloorNum() {
        house.setFloorNum(2);
        assertEquals(2, house.getFloorNum());
        assertThrows(NumberFormatException.class, () -> {
            house.setFloorNum(-1);
        });
    }


    @Test
    public void testNeighbors() {
        List<Neighbor> neighbors = new ArrayList<>();
        neighbors.add(neighbor);
        house.setNeighbors(neighbors);
        assertEquals(neighbors, house.getNeighbors());
    }

    @Test
    public void testImages() {
        List<String> images = new ArrayList<>();
        images.add("A new image");
        house.setImages(images);
        assertEquals(images, house.getImages());
    }

    @Test
    public void testInfoStatus() {
        house.setInfoStatus(InfoStatus.DIRTY);
        assertEquals(InfoStatus.DIRTY, house.getInfoStatus());
    }

    @Test
    public void testSaleContract() {
        SaleContract saleContract = new SaleContract(null, null, SaleStatus.AVAILABLE);
        house.setSaleContract(saleContract);
        assertEquals(saleContract, house.getSaleContract());
    }

    @Test
    public void testHouseRate() {
        HouseRate houseRate = new HouseRate();
        house.setHouseRate(houseRate);
        assertEquals(houseRate, house.getHouseRate());
    }

    @Test
    public void testHouseClassificationByGender() {
        house.setHouseClassificationByGender(HouseClassificationByGender.FEMALE);
        assertEquals(HouseClassificationByGender.FEMALE, house.getHouseClassificationByGender());
    }

    @Test
    public void testAddNeighbor() {
        // test add a new neighbor
        house.addNeighbor(neighbor);
        assertTrue(house.getNeighbors().contains(neighbor));

        // test add an existing one
        int neighborsSize = house.getNeighbors().size();
        house.addNeighbor(neighbor);
        assertEquals(neighborsSize, house.getNeighbors().size());
    }

    @Test
    public void testRemoveNeighbor() {
        house.addNeighbor(neighbor);
        house.removeNeighbor(neighbor);
        assertFalse(house.getNeighbors().contains(neighbor));
    }

    @Test
    public void testAddImage() {
        // test add a new image
        house.addImage("A new Image");
        assertTrue(house.getImages().contains("A new Image"));

        // test add an existing one
        int imagesSize = house.getImages().size();
        house.addImage("A new Image");
        assertEquals(imagesSize, house.getImages().size());
    }

    @Test
    public void testEquals() {
        // object from another type
        assertNotEquals(house, new Object());
        // equal
        assertEquals(house, new House(1,null, -1 ,1, HouseClassificationByGender.FAMILY));
        // unequal
        assertNotEquals(house, new House(2, services, 400 ,1, HouseClassificationByGender.MALE));
    }

    @Test
    public void testHashCode() {
        House anotherHouse = new House(1, null, -1, 1, HouseClassificationByGender.FAMILY);
        assertEquals(house.hashCode(), anotherHouse.hashCode());
    }
}
