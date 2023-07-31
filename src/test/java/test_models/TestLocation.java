package test_models;

import models.Location;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

public class TestLocation {
    private Location location;

    @Before
    public void setup() {
        location = new Location("Nablus","Tunis Street");
    }

    @Test
    public void testCity() {
        location.setCity("Jenin");
        assertEquals("Jenin",location.getCity());
    }

    @Test
    public void testStreet() {
        location.setStreet("Abu-Baker Street");
        assertEquals("Abu-Baker Street",location.getStreet());
    }

    @Test
    public void testEquals() {
        // object from another type
        assertNotEquals(location, new Object());
        // unequal city
        assertNotEquals(location, new Location("Jerusalem","Tunis Street"));
        // unequal street
        assertNotEquals(location, new Location("Nablus","Main Street"));
        // only city
        assertEquals(location, new Location("Nablus",""));
        // only street (ignore case)
        assertEquals(location, new Location("","Tunis street"));
        // street and number
        assertEquals(location, new Location("","Tunis Street"));
    }

    @Test
    public void testHashCode() {
        Location anotherLocation = new Location("Nablus","Tunis Street");
        assertEquals(location.hashCode(), anotherLocation.hashCode());
    }

}
