package objects;

import enums.UserType;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class TestSakancom {
    private static User user;
    private static Building building;

    @Before
    public void setup() {
        user = new User("mo-alawneh","Mohammad62002",UserType.ADMIN,null,null,null);
        building = new Building(1, "Golden Building", null, null);
    }

    @Test
    public void testUsers() {
        List<User> users = new ArrayList<>();
        users.add(user);
        Sakancom.setUsers(users);
        assertEquals(users, Sakancom.getUsers());
    }

    @Test
    public void testBuildings() {
        List<Building> buildings = new ArrayList<>();
        buildings.add(building);
        Sakancom.setBuildings(buildings);
        assertEquals(buildings, Sakancom.getBuildings());
    }

    @Test
    public void testAddUser() {
        // success add
        User newUser = new User("najat-mansour","Najat12003",UserType.ADMIN, null, null, null);
        Sakancom.addUser(newUser);
        assertTrue(Sakancom.getUsers().contains(newUser));
        // failed add
        Sakancom.addUser(user);
        assertEquals(2, Sakancom.getUsers().size());
    }

    @Test
    public void testRemoveUser() {
        assertTrue(Sakancom.getUsers().contains(user));
        Sakancom.removeUser(user);
        assertFalse(Sakancom.getUsers().contains(user));
    }

    @Test
    public void testAddBuilding() {
        // success add
        Building newBuilding = new Building(2,"Happiness House",null, null);
        Sakancom.addBuilding(newBuilding);
        assertTrue(Sakancom.getBuildings().contains(newBuilding));
        // failed add
        Sakancom.addBuilding(building);
        assertEquals(2, Sakancom.getBuildings().size());
    }

    @Test
    public void testRemoveBuilding() {
        assertTrue(Sakancom.getBuildings().contains(building));
        Sakancom.removeBuilding(building);
        assertFalse(Sakancom.getBuildings().contains(building));
    }

    @Test
    public void testSearchAboutUsers() throws ParseException {
        Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse("12/06/2002");
        // insert an admin
        Name adminName = new Name("Ahmad","Mohammad","Bale");
        UserLocation adminLocation = new UserLocation("Jenin","Abu-Baker","Personal Building",1);
        ContactInfo adminContactInfo = new ContactInfo("s12028067@stu.najah.edu","0592838433",birthDate,"Computer Engineering");
        User admin = new User("user1","12345678",UserType.ADMIN, adminName, adminLocation, adminContactInfo);
        Sakancom.addUser(admin);
        // insert an owner
        // insert a tenant
    }
}
