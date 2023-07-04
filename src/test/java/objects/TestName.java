package objects;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

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
}
