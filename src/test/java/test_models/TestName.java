package test_models;

import models.Name;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestName {
    private Name name;
    @Before
    public void setup() {
        name = new Name("Mohammad","Abd-Allateef","Alawneh");
    }

    @Test
    public void testFirstName() {
        name.setFirstName("Najat");
        assertEquals("Najat",name.getFirstName());
    }

    @Test
    public void testMiddleName() {
        name.setMiddleName("Sameer");
        assertEquals("Sameer",name.getMiddleName());
    }

    @Test
    public void testLastName() {
        name.setLastName("Mansour");
        assertEquals("Mansour",name.getLastName());
    }

    @Test
    public void testEquals() {
        // object from another type
        assertNotEquals(name, new Object());
        // equal (Ignore case)
        assertEquals(name, new Name("moHammad","Abd-Allateef","alawneh"));
        // equal with ignoring first name
        assertEquals(name, new Name("","Abd-Allateef","alawneh"));
        // equal with ignoring middle name
        assertEquals(name, new Name("moHammad","","alawneh"));
        // equal with ignoring last name
        assertEquals(name, new Name("moHammad","Abd-Allateef",""));
        // unequal
        assertNotEquals(name, new Name("Najat","Abd-Allateef","alawneh"));
    }

    @Test
    public void testHashCode() {
        Name anotherName = new Name ("Mohammad","Abd-Allateef","Alawneh");
        assertEquals(name.hashCode(), anotherName.hashCode());
    }
}
