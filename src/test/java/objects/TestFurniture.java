package objects;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestFurniture {
    private Furniture furniture;

    @Before
    public void setup() {
        furniture = new Furniture("A furniture Description","A furniture image");
    }

    @Test
    public void testDescription() {
        furniture.setDescription("A new description");
        assertEquals("A new description", furniture.getDescription());
    }

    @Test
    public void testImage() {
        furniture.setImage("A new image");
        assertEquals("A new image", furniture.getImage());
    }

    @Test
    public void testEquals() {
        // object from another type
        assertNotEquals(furniture, new Object());
        // equal
        assertEquals(furniture, new Furniture("A furniture Description","A furniture image"));
        // unequal
        assertNotEquals(furniture, new Furniture("description","A furniture image"));
    }

    @Test
    public void testHashCode() {
        Furniture anotherFurniture = new Furniture("A furniture Description","A furniture image");
        assertEquals(furniture.hashCode(), anotherFurniture.hashCode());
    }
}
