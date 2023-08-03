package test_models;

import exceptions.InvalidEmailFormatException;
import models.ContactInfo;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertThrows;

public class TestContactInfo {
    private ContactInfo contactInfo;

    @Before
    public void setup() throws ParseException {
        Date birthdate = new SimpleDateFormat("dd/MM/yyyy").parse("12/06/2002");
        contactInfo = new ContactInfo("mo.a.alawneh@gmail.com","0592838433", birthdate,"Computer Engineering");
    }

    @Test
    public void testEmail() throws InvalidEmailFormatException {
        contactInfo.setEmail("s12028067@stu.najah.edu");
        assertEquals("s12028067@stu.najah.edu",contactInfo.getEmail());
        assertThrows(InvalidEmailFormatException.class, () -> contactInfo.setEmail("f@f"));
    }

    @Test
    public void testPhoneNumber() {
        contactInfo.setPhoneNumber("0599716597");
        assertEquals("0599716597",contactInfo.getPhoneNumber());
    }

    @Test
    public void testBirthdate() throws ParseException {
        Date birthdate = new SimpleDateFormat("dd/MM/yyyy").parse("28/01/2003");
        contactInfo.setBirthdate(birthdate);
        assertEquals(birthdate,contactInfo.getBirthdate());
    }

    @Test
    public void testMajor() {
        contactInfo.setMajor("Electrical Engineering");
        assertEquals("Electrical Engineering",contactInfo.getMajor());
    }
}
