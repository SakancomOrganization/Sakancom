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
        // equal with sub first name -first-
        assertEquals(name, new Name("moh","Abd-Allateef","alawneh"));
        // equal with sub first name -middle-
        assertEquals(name, new Name("ad","Abd-Allateef","alawneh"));
        // equal with sub first name -end-
        assertEquals(name, new Name("mm","Abd-Allateef","alawneh"));
        // equal with sub middle name -first-
        assertEquals(name, new Name("moHammad","Abd","alawneh"));
        // equal with sub middle name -middle-
        assertEquals(name, new Name("moHammad","Alla","alawneh"));
        // equal with sub middle name -end-
        assertEquals(name, new Name("moHammad","Allateef","alawneh"));
        // unequal
        assertNotEquals(name, new Name("Najat","Abd-Allateef","alawneh"));
    }

    @Test
    public void testHashCode() {
        Name anotherName = new Name ("Mohammad","Abd-Allateef","Alawneh");
        assertEquals(name.hashCode(), anotherName.hashCode());
    }
}
