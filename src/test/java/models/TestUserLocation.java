package models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestUserLocation {
    private UserLocation userLocation;

    @Before
    public void setup() {
        userLocation = new UserLocation("Jenin","Abu-Baker","Personal Building",1);
    }

    @Test
    public void testBuilding() {
        userLocation.setBuilding("Personal Building");
        assertEquals("Personal Building", userLocation.getBuilding());
    }

    @Test
    public void testFloorNum() {
        userLocation.setFloorNum(2);
        assertEquals(2, userLocation.getFloorNum());
    }

    @Test
    public void testEquals() {
        // object from another type
        assertNotEquals(userLocation, new Object());
        // equals
        assertEquals(userLocation, new UserLocation("Jenin","Abu-Baker","Personal Building",1));
        // unequal due to string in the super (location)
        assertNotEquals(userLocation, new UserLocation("Nablus","Abu-Baker","Personal Building",1));
        // unequal due to the building string (specialized member field)
        assertNotEquals(userLocation, new UserLocation("Jenin","Abu-Baker","Personal Building",2));
        // unequal due to the floor number (specialized member field)
        assertNotEquals(userLocation, new UserLocation("Jenin","Abu-Baker","4070",2));
    }

    @Test
    public void testHashCode() {
        UserLocation anotherUserLocation = new UserLocation("Jenin","Abu-Baker","Personal Building",1);
        assertEquals(userLocation.hashCode(), anotherUserLocation.hashCode());
    }
}
