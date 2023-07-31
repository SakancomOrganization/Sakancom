package test_email;

import email.YmlHandler;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

public class TestYmlHandler {
    @Test
    public void testGetValue() throws FileNotFoundException {
        Assert.assertEquals("mo.a.alawneh@gmail.com", YmlHandler.getValue("fromEmail"));
        assertEquals("oalhpnzxavixrdpq", YmlHandler.getValue("password"));
    }
}
