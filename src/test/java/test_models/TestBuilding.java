package test_models;

import enums.HouseClassificationByGender;
import enums.InfoStatus;
import enums.UserType;
import exceptions.AlreadyFoundElementException;
import exceptions.HouseNotFoundException;
import exceptions.InvalidEmailFormatException;
import exceptions.UnacceptableValueException;
import models.*;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestBuilding {
    private Location location;
    private User owner;
    private Building building;
    private House house;

    @Before
    public void setup() throws ParseException, UnacceptableValueException, InvalidEmailFormatException {
        location = new Location("Jenin","Abu-Baker");
        owner = new User("mo-alawneh",
                "Mohammad12002",
                UserType.OWNER,
                new Name("Mohammad","AbdAllateef","Alawneh"),
                new UserLocation("Jenin","Abu-Baker Street","4070",1),
                new ContactInfo("mo.a.alawneh@gmail.com","0592838433",new SimpleDateFormat("dd/MM/yyyy").parse("12/06/2002"),"Computer Engineering"));
        building = new Building(1, "Golden House", owner, null);
        house = new House(1, null, 3000, 1, HouseClassificationByGender.FAMILY);
    }

    @Test
    public void testId() {
        building.setId(2);
        assertEquals(2, building.getId());
    }

    @Test
    public void testName() {
        building.setName("Happiness Building");
        assertEquals("Happiness Building", building.getName());
    }

    @Test
    public void testOwner() {
        building.setOwner(owner);
        assertEquals(owner, building.getOwner());
    }

    @Test
    public void testLocation() {
        building.setLocation(location);
        assertEquals(location, building.getLocation());
    }

    @Test
    public void testHouses() throws AlreadyFoundElementException {
        List<House> houses = new ArrayList<>();
        houses.add(house);
        building.setHouses(houses);
        assertEquals(houses, building.getHouses());
    }

    @Test
    public void testInfoStatus() {
        building.setInfoStatus(InfoStatus.ACCEPTED);
        assertEquals(InfoStatus.ACCEPTED, building.getInfoStatus());
    }

    @Test
    public void testAddHouse() throws AlreadyFoundElementException {
        // add success
        building.addHouse(house);
        assertTrue(building.getHouses().contains(house));

        // test adding an existing one
        assertThrows(AlreadyFoundElementException.class, () -> building.addHouse(house));
    }

    @Test
    public void testRemoveHouse() throws AlreadyFoundElementException {
        building.addHouse(house);
        building.removeHouse(house);
        assertFalse(building.getHouses().contains(house));
    }

    @Test
    public void testGetHouseById() throws AlreadyFoundElementException, UnacceptableValueException, HouseNotFoundException {
        building.addHouse(house);
        House resultedHouse = new House(1, null, -1 ,1, null);
        assertEquals(resultedHouse, building.getHouseById(1));
        assertThrows(HouseNotFoundException.class, () -> building.getHouseById(10));
    }

    @Test
    public void testEquals() {
        // object from another type
        assertNotEquals(building, new Object());
        // equal with the same id and name
        assertEquals(building, new Building(1, "Golden Building",null, null));
        // equal because of id
        assertEquals(building, new Building(1, "Illegal Name", null, null));
        // equal because of name and owner
        assertEquals(building, new Building(2, "Golden House", owner, null));
        // unequal
        assertNotEquals(building, new Building(3, "Illegal Name",null, null));
    }

    @Test
    public void testHashCode() {
        Building anotherBuilding = new Building(1, "Golden House", null, null);
        assertEquals(building.hashCode(), anotherBuilding.hashCode());
    }
}
