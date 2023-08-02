package test_models;

import exceptions.UnacceptableValueException;
import models.UserLocation;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestUserLocation {
    private UserLocation userLocation;

    @Before
    public void setup() throws UnacceptableValueException {
        userLocation = new UserLocation("Jenin","Abu-Baker","Personal Building",1);
    }

    @Test
    public void testBuilding() {
        userLocation.setBuilding("Personal Building");
        assertEquals("Personal Building", userLocation.getBuilding());
    }

    @Test
    public void testFloorNum() throws UnacceptableValueException {
        userLocation.setFloorNum(2);
        assertEquals(2, userLocation.getFloorNum());
        assertThrows(UnacceptableValueException.class, () -> userLocation.setFloorNum(-1));
    }

    @Test
    public void testEquals() throws UnacceptableValueException {
        // object from another type
        assertNotEquals(userLocation, new Object());
        // equals
        assertEquals(userLocation, new UserLocation("Jenin","Abu-Baker","Personal Building",1));
        // sub string tests
        assertEquals(userLocation, new UserLocation("Jenin","Abu-Baker","PErsoNal",1));
        assertEquals(userLocation, new UserLocation("Jenin","Abu-Baker","Building",1));
        assertEquals(userLocation, new UserLocation("Jenin","Abu-Baker","sonal Bui",1));
        // unequal due to string in the super (location)
        assertNotEquals(userLocation, new UserLocation("Nablus","Abu-Baker","Personal Building",1));
        // unequal due to the building string (specialized member field)
        assertNotEquals(userLocation, new UserLocation("Jenin","Abu-Baker","Personal Building",2));
        // unequal due to the floor number (specialized member field)
        assertNotEquals(userLocation, new UserLocation("Jenin","Abu-Baker","4070",2));
    }

    @Test
    public void testHashCode() throws UnacceptableValueException {
        UserLocation anotherUserLocation = new UserLocation("Jenin","Abu-Baker","Personal Building",1);
        assertEquals(userLocation.hashCode(), anotherUserLocation.hashCode());
    }
}
