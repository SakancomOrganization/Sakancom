package test_models;

import enums.UserType;
import exceptions.AlreadyFoundElementException;
import exceptions.BuildingNotFoundException;
import exceptions.UnacceptableValueException;
import exceptions.UserNotFoundException;
import models.Building;
import models.Sakancom;
import models.User;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class TestSakancom {
    private static User user;
    private static Building building;

    @Before
    public void setup() throws UnacceptableValueException, ParseException, AlreadyFoundElementException {
        Sakancom.initSakancomWithData();
        user = new User("test-user", "Test1234", UserType.ADMIN, null, null, null);
        building = new Building(2, "Test Building", null, null);
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
        User newUser = new User("test-user", "Test4321", UserType.ADMIN, null, null, null);
        assertThrows(AlreadyFoundElementException.class, () -> Sakancom.addUser(newUser));
    }

    @Test
    public void testRemoveUser() throws AlreadyFoundElementException {
        Sakancom.addUser(user);
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
        Building newBuilding = new Building(1, "Happiness House", null, null);
        assertThrows(AlreadyFoundElementException.class, () -> Sakancom.addBuilding(newBuilding));
    }

    @Test
    public void testRemoveBuilding() throws AlreadyFoundElementException {
        Sakancom.addBuilding(building);
        Sakancom.removeBuilding(building);
        assertFalse(Sakancom.getBuildings().contains(building));
    }

    @Test
    public void testGetUserByUsername() throws UserNotFoundException {
        User resultedUser = new User("mo-alawneh", "", null, null, null, null);
        assertEquals(resultedUser, Sakancom.getUserByUsername("mo-alawneh"));
        assertThrows(UserNotFoundException.class, () -> Sakancom.getUserByUsername("mohammad-al"));
    }

    @Test
    public void testGetBuildingById() throws BuildingNotFoundException {
        Building resultedBuilding = new Building(1, "", null, null);
        assertEquals(resultedBuilding, Sakancom.getBuildingById(1));
        assertThrows(BuildingNotFoundException.class, () -> Sakancom.getBuildingById(10));
    }
}
