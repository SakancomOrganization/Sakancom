package test_io;

import io.YmlHandler;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

public class TestYmlHandler {
    @Test
    public void testGetValue() throws FileNotFoundException {
        assertEquals("mo.a.alawneh@gmail.com", YmlHandler.getValue("fromEmail"));
        assertEquals("oalhpnzxavixrdpq", YmlHandler.getValue("password"));
        assertEquals("C:\\Users\\HITECH\\IdeaProjects\\Sakancom\\src\\main\\resources\\images/building_"
                , YmlHandler.getValue("path"));
    }
}
