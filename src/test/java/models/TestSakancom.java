package models;

import enums.HouseClassificationByGender;
import enums.UserType;
import exceptions.AlreadyFoundElementException;
import exceptions.UnacceptableValueException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestSakancom {
    private static User user;
    private static Building building;

    @BeforeClass
    public static void setupData() throws ParseException, UnacceptableValueException {
        Sakancom.initSakancomWithData();
    }

    @Before
    public void setup()  {
        user = new User("test-user","Test1234",UserType.ADMIN,null,null,null);
        building = new Building(-1, "Test Building", null, null);
    }

    @Test
    public void testCurrentUser() {
        // initially, currentUser = null
        assertNull(Sakancom.getCurrentUser());
        // set currentUser
        Sakancom.setCurrentUser(user);
        // test
        assertEquals(user, Sakancom.getCurrentUser());
    }

    @Test
    public void testAddUser() throws AlreadyFoundElementException {
        // success add
        Sakancom.addUser(user);
        assertTrue(Sakancom.getUsers().contains(user));

        // testing adding an existing one
        User newUser = new User("test-user","Test4321",UserType.ADMIN, null, null, null);
        assertThrows(AlreadyFoundElementException.class, () -> {
           Sakancom.addUser(newUser);
        });
    }

    @Test
    public void testRemoveUser() {
        assertTrue(Sakancom.getUsers().contains(user));
        Sakancom.removeUser(user);
        assertFalse(Sakancom.getUsers().contains(user));
    }

    @Test
    public void testAddBuilding() throws AlreadyFoundElementException {
        // success add
        Sakancom.addBuilding(building);
        assertTrue(Sakancom.getBuildings().contains(building));

        // test adding an existing one
        Building newBuilding = new Building(1,"Happiness House",null, null);
        assertThrows(AlreadyFoundElementException.class, () -> {
            Sakancom.addBuilding(newBuilding);
        });
    }

    @Test
    public void testRemoveBuilding() throws AlreadyFoundElementException {
        Sakancom.addBuilding(building);
        Sakancom.removeBuilding(building);
        assertFalse(Sakancom.getBuildings().contains(building));
    }

    @Test
    public void testSearchAboutUsers() throws UnacceptableValueException, ParseException {
        User user = new User("mo-alawneh",
                "Mohammad62002",
                UserType.ADMIN,
                new Name("Mohammad","AbdAllateef","Alawneh"),
                null,
                null);
        List<User> result = new ArrayList<>();
        result.add(user);

        // search based on the username
        assertEquals(result, Sakancom.searchAboutUsers("mo-alawneh",UserType.ADMIN,null, "", "", ""));
        // search based on the username and name
        assertEquals(result, Sakancom.searchAboutUsers("mo-alawneh",UserType.ADMIN, new Name("mohammad","AbdAllateef","alawneh"), "", "", ""));
        // search based on the username, name and email
        assertEquals(result, Sakancom.searchAboutUsers("mo-alawneh",UserType.ADMIN, new Name("mohammad","AbdAllateef","alawneh"), "mo.a.alawneh@gmail.com", "", ""));
        // search based on the username, name, email and phone number
        assertEquals(result, Sakancom.searchAboutUsers("mo-alawneh",UserType.ADMIN, new Name("mohammad","AbdAllateef","alawneh"), "mo.a.alawneh@gmail.com", "0592838433", ""));
        // search based on the username, name, email, phone number and major
        assertEquals(result, Sakancom.searchAboutUsers("mo-alawneh",UserType.ADMIN, new Name("mohammad","AbdAllateef","alawneh"), "mo.a.alawneh@gmail.com", "0592838433", "computer engineering"));
        // failed search
        assertEquals(List.of(), Sakancom.searchAboutUsers("ali-2002",UserType.ADMIN,null, "", "", ""));
        assertEquals(List.of(), Sakancom.searchAboutUsers("najat-mansour",UserType.ADMIN,new Name("mohammad","AbdAllateef","alawneh"), "", "", ""));
        assertEquals(List.of(), Sakancom.searchAboutUsers("mo-alawneh",UserType.ADMIN,new Name("bale","AbdAllateef","alawneh"), "", "", ""));
        assertEquals(List.of(), Sakancom.searchAboutUsers("mo-alawneh",UserType.OWNER, new Name("mohammad","AbdAllateef","alawneh"), "mo.a.alawneh@gmail.com", "0592838433", "computer engineering"));
        assertEquals(List.of(), Sakancom.searchAboutUsers("mo-alawneh",UserType.ADMIN, new Name("mohammad","AbdAllateef","alawneh"), "mo.a.alawneh@hotmail.com", "0592838433", "computer engineering"));
        assertEquals(List.of(), Sakancom.searchAboutUsers("mo-alawneh",UserType.ADMIN, new Name("mohammad","AbdAllateef","alawneh"), "mo.a.alawneh@gmail.com", "0592123456", "computer engineering"));
        assertEquals(List.of(), Sakancom.searchAboutUsers("mo-alawneh",UserType.ADMIN, new Name("mohammad","AbdAllateef","alawneh"), "mo.a.alawneh@gmail.com", "0592838433", "electrical engineering"));
    }

    @Test
    public void testGetUserByUsername() {
        User resultedUser = new User("mo-alawneh", "", null, null, null, null);
        assertEquals(resultedUser, Sakancom.getUserByUsername("mo-alawneh"));
        assertNull(Sakancom.getUserByUsername("mohammad-al"));
    }

    @Test
    public void testGetBuildingById() {
        Building resultedBuilding = new Building(1, "", null, null);
        assertEquals(resultedBuilding, Sakancom.getBuildingById(1));
        assertNull(Sakancom.getBuildingById(10));
    }

    @Test
    public void testSearchAboutBuildings() throws UnacceptableValueException, ParseException {
        Building resultedBuilding = new Building(1,
                "Golden House",
                null,
                null);
        List<Building> buildings = new ArrayList<>();
        buildings.add(resultedBuilding);

        // search based on id
        assertEquals(buildings, Sakancom.searchAboutBuildings(1, "", null, null));
        // search based on name
        assertEquals(buildings, Sakancom.searchAboutBuildings(-1, "golden house", null, null));
        // search based on id and name
        assertEquals(buildings, Sakancom.searchAboutBuildings(1, "golden house", null, null));
        // search based on id, name and owner
        User owner = new User("haya-sam","", UserType.OWNER, null, null, null);
        assertEquals(buildings, Sakancom.searchAboutBuildings(1, "golden house", owner, null));
        // search based on id, name, owner and location
        Location location = new Location("Nablus", "Rafidia");
        assertEquals(buildings, Sakancom.searchAboutBuildings(1, "golden house", owner, location));
        // failed search
        assertEquals(List.of(), Sakancom.searchAboutBuildings(3,"", null, null));
        assertEquals(List.of(), Sakancom.searchAboutBuildings(1,"Happiness House", null, null));
        owner.setUsername("mo-alawneh");
        assertEquals(List.of(), Sakancom.searchAboutBuildings(1,"Golden House", owner, null));
        owner.setUsername("mo-alawneh");
        location.setCity("Jenin");
        assertEquals(List.of(), Sakancom.searchAboutBuildings(1,"Golden House", owner, location));
    }

    @Test
    public void testSearchAboutHouses() throws UnacceptableValueException, ParseException {
        House resultedHouse = new House(1,
                null,
                2000,
                1,
                HouseClassificationByGender.FAMILY);
        List<House> houses = new ArrayList<>();
        houses.add(resultedHouse);

        Services services = new Services(true, true, true, true, true, 3, 2);
        User owner = new User("haya-sam","", UserType.OWNER, null, null, null);
        Location location = new Location("Nablus", "Rafidia");

        // search based on services
        assertEquals(houses, Sakancom.searchAboutHouses(services, -1, null, null, null));
        // search based on monthly rent
        assertEquals(houses, Sakancom.searchAboutHouses(null, 2000, null, null, null));
        // search based on services, monthly rent and owner
        assertEquals(houses, Sakancom.searchAboutHouses(services, 3000, owner, null, null));
        // search based on services, monthly rent, owner and location
        assertEquals(houses, Sakancom.searchAboutHouses(services, 3000, owner, location, null));
        // search based on services, monthly rent, owner, location and classification field
        assertEquals(houses, Sakancom.searchAboutHouses(services, 3000, owner, location, HouseClassificationByGender.FAMILY));
        // search failed
        assertEquals(List.of(), Sakancom.searchAboutHouses(services, 1000, owner, location, HouseClassificationByGender.FAMILY));
        assertEquals(List.of(), Sakancom.searchAboutHouses(services, 3000, owner, location, HouseClassificationByGender.FEMALE));
        owner.setUsername("mohammad-al");
        assertEquals(List.of(), Sakancom.searchAboutHouses(services, 1000, owner, location, HouseClassificationByGender.FAMILY));
        owner.setUsername("haya-sam");
        services.setHasBalcony(false);
        assertEquals(List.of(), Sakancom.searchAboutHouses(services, 1000, owner, location, HouseClassificationByGender.FAMILY));
        owner.setUsername("haya-sam");
        services.setHasBalcony(true);
        services.setBathroomsNum(4);
        assertEquals(List.of(), Sakancom.searchAboutHouses(services, 1000, owner, location, HouseClassificationByGender.FAMILY));
        owner.setUsername("haya-sam");
        services.setHasBalcony(true);
        services.setBathroomsNum(3);
        location.setCity("Jenin");
        assertEquals(List.of(), Sakancom.searchAboutHouses(services, 1000, owner, location, HouseClassificationByGender.FAMILY));
    }
}
