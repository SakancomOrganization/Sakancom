package models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestServices {
    private Services services;

    @Before
    public void setup() {
        services = new Services(true, true, true, true, false, 4, 3);
    }

    @Test
    public void testIncludesElectricity() {
        services.setIncludesElectricity(false);
        assertFalse(services.isIncludesElectricity());
    }

    @Test
    public void testIncludesWater() {
        services.setIncludesWater(false);
        assertFalse(services.isIncludesWater());
    }

    @Test
    public void testHasInternet() {
        services.setHasInternet(false);
        assertFalse(services.isHasInternet());
    }

    @Test
    public void testHasTelephone() {
        services.setHasTelephone(false);
        assertFalse(services.isHasTelephone());
    }

    @Test
    public void testHasBalcony() {
        services.setHasBalcony(true);
        assertTrue(services.isHasBalcony());
    }

    @Test
    public void testBedroomsNum() {
        services.setBedroomsNum(3);
        assertEquals(3, services.getBedroomsNum());
    }

    @Test
    public void testBathroomsNum() {
        services.setBathroomsNum(1);
        assertEquals(1, services.getBathroomsNum());
    }

    @Test
    public void testEquals() {
        // object from another type
        assertNotEquals(services, new Object());
        // equal
        assertEquals(services, new Services(true, true, true, true, false, 4, 3));
        // increase the bedrooms and bedrooms number
        assertEquals(services, new Services(true, true, true, true, false, 4, 5));
        // unequal because of boolean
        assertNotEquals(services, new Services(true, false, true, true, false, 4, 3));
        // unequal because of rooms number
        assertNotEquals(services, new Services(true, true, true, true, false, 1, 3));
    }

    @Test
    public void testHashCode() {
        Services anotherServices = new Services(true, true, true, true, false, 4, 3);
        assertEquals(services.hashCode(), anotherServices.hashCode());
    }
}
