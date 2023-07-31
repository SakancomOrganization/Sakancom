package models;

import enums.HouseClassificationByGender;
import enums.InfoStatus;
import enums.SaleStatus;
import exceptions.AlreadyFoundElementException;
import exceptions.UnacceptableValueException;
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
    public void setup() throws UnacceptableValueException {
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
    public void testMonthlyRent() throws UnacceptableValueException {
        house.setMonthlyRent(200);
        assertEquals(200, house.getMonthlyRent());
        assertThrows(UnacceptableValueException.class, () -> {
           house.setMonthlyRent(-100);
        });
    }

    @Test
    public void testFloorNum() throws UnacceptableValueException {
        house.setFloorNum(2);
        assertEquals(2, house.getFloorNum());
        assertThrows(UnacceptableValueException.class, () -> {
            house.setFloorNum(-1);
        });
    }


    @Test
    public void testNeighbors() throws AlreadyFoundElementException {
        List<Neighbor> neighbors = new ArrayList<>();
        neighbors.add(neighbor);
        house.setNeighbors(neighbors);
        assertEquals(neighbors, house.getNeighbors());
    }

    @Test
    public void testImages() throws AlreadyFoundElementException {
        List<String> images = new ArrayList<>();
        images.add("A new image");
        house.setImages(images);
        assertEquals(images, house.getImages());
    }

    @Test
    public void testInfoStatus() {
        house.setInfoStatus(InfoStatus.ACCEPTED);
        assertEquals(InfoStatus.ACCEPTED, house.getInfoStatus());
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
    public void testAddNeighbor() throws AlreadyFoundElementException {
        // test add a new neighbor
        house.addNeighbor(neighbor);
        assertTrue(house.getNeighbors().contains(neighbor));

        // test add an existing one
        assertThrows(AlreadyFoundElementException.class, () -> {
            house.addNeighbor(neighbor);
        });
    }

    @Test
    public void testRemoveNeighbor() throws AlreadyFoundElementException {
        house.addNeighbor(neighbor);
        house.removeNeighbor(neighbor);
        assertFalse(house.getNeighbors().contains(neighbor));
    }

    @Test
    public void testAddImage() throws AlreadyFoundElementException {
        // test add a new image
        house.addImage("A new Image");
        assertTrue(house.getImages().contains("A new Image"));

        // test add an existing one
        assertThrows(AlreadyFoundElementException.class, () -> {
           house.addImage("A new Image");
        });
    }

    @Test
    public void testEquals() throws UnacceptableValueException {
        // object from another type
        assertNotEquals(house, new Object());
        // equal
        assertEquals(house, new House(1,null, -1 ,1, HouseClassificationByGender.FAMILY));
        // unequal
        assertNotEquals(house, new House(2, services, 400 ,1, HouseClassificationByGender.MALE));
    }

    @Test
    public void testHashCode() throws UnacceptableValueException {
        House anotherHouse = new House(1, null, -1, 1, HouseClassificationByGender.FAMILY);
        assertEquals(house.hashCode(), anotherHouse.hashCode());
    }
}
