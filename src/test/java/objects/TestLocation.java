package objects;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

public class TestLocation {
    private Location location;

    @Before
    public void setup() {
        location = new Location("Nablus","Tunis Street","4070",4);
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
    public void testBuilding() {
        location.setBuilding("4400");
        assertEquals("4400",location.getBuilding());
    }

    @Test
    public void testFloorNum() {
        location.setFloorNum(5);
        assertEquals(5,location.getFloorNum());
    }

    @Test
    public void testEquals() {
        // object from another type
        assertNotEquals(location, new Object());
        // unequal city
        assertNotEquals(location, new Location("Jerusalem","Tunis Street","4070",4));
        // unequal street
        assertNotEquals(location, new Location("Nablus","Main Street","4070",4));
        // only city
        assertEquals(location, new Location("Nablus","","",-1));
        // only street (ignore case)
        assertEquals(location, new Location("","Tunis street","",-1));
        // street and number
        assertEquals(location, new Location("","Tunis Street","",4));
        // street and number (false)
        assertNotEquals(location, new Location("","Tunis Street","",3));
    }

    @Test
    public void testHashCode() {
        Location anotherLocation = new Location("Nablus","Tunis Street","4070",4);
        assertEquals(location.hashCode(), anotherLocation.hashCode());
    }

}
