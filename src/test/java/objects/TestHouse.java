package objects;

import enums.InfoStatus;
import enums.SaleStatus;
import enums.UserType;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestHouse {
    private House house;
    private User owner;
    private Location location;
    private Services services;
    private Neighbor neighbor;

    @Before
    public void setup() {
        neighbor = new Neighbor("Anas", "A very quiet neighbor");
        house = new House(1, null, null, null, 400);
    }

    @Test
    public void testId() {
        house.setId(2);
        assertEquals(2, house.getId());
    }

    @Test
    public void testOwner() throws ParseException {
        owner = new User("mo-alawneh",
                "Mohammad12002",
                UserType.OWNER,
                new Name("Mohammad","AbdAllateef","Alawneh"),
                new Location("Jenin","Abu-Baker Street","4070",1),
                new ContactInfo("mo.a.alawneh@gmail.com","0592838433",new SimpleDateFormat("dd/MM/yyyy").parse("12/06/2002"),"Computer Engineering"));
        house.setOwner(owner);
        assertEquals(owner, house.getOwner());
    }

    @Test
    public void testLocation() {
        location = new Location("Nablus", "Al-Etihad Street", "Personal Building", 1);
        house.setLocation(location);
        assertEquals(location, house.getLocation());
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
    public void testSaleContract() throws ParseException {
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
    public void testAddNeighbor() {
        // test add a new neighbor
        house.getNeighbors().add(neighbor);
        assertTrue(house.getNeighbors().contains(neighbor));

        // test add an existing one
        int neighborsSize = house.getNeighbors().size();
        house.addNeighbor(neighbor);
        assertEquals(neighborsSize, house.getNeighbors().size());
    }

    @Test
    public void testRemoveNeighbor() {
        house.removeNeighbor(neighbor);
        assertFalse(house.getNeighbors().contains(neighbor));
    }

    @Test
    public void testAddImage() {
        // test add a new image
        house.getImages().add("A new Image");
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
        assertEquals(house, new House(1,null, null, null, -1));
        // unequal
        assertNotEquals(house, new House(2, owner, location, services, 400));
    }

    @Test
    public void testHashCode() {
        House anotherHouse = new House(1, null, null, null, -1);
        assertEquals(house.hashCode(), anotherHouse.hashCode());
    }
}
