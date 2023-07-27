package email;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

public class TestYmlHandler {
    @Test
    public void testGetValue() throws FileNotFoundException {
        assertEquals("mo.a.alawneh@gmail.com", YmlHandler.getValue("fromEmail"));
        assertEquals("oalhpnzxavixrdpq", YmlHandler.getValue("password"));
    }
}
