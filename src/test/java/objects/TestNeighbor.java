package objects;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestNeighbor {
    private Neighbor neighbor;

    @Before
    public void setup() {
        neighbor = new Neighbor("Aws","Has a night work");
    }

    @Test
    public void testName() {
        neighbor.setName("Mohammad");
        assertEquals("Mohammad",neighbor.getName());
    }

    @Test
    public void testDescriptionName() {
        neighbor.setDescription("Very quiet neighbor");
        assertEquals("Very quiet neighbor",neighbor.getDescription());
    }

    @Test
    public void testEquals() {
        // object from another type
        assertNotEquals(neighbor, new Object());
        // equal (ignore case)
        assertEquals(neighbor, new Neighbor("Aws","Has a night WorK"));
        // unequal
        assertNotEquals(neighbor, new Neighbor("Ahmad","Has a night work"));
    }

    @Test
    public void testHashCode() {
        Neighbor anotherNeighbor = new Neighbor("Aws","Has a night work");
        assertEquals(neighbor.hashCode(), anotherNeighbor.hashCode());
    }
}
