package objects;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static junit.framework.TestCase.assertEquals;

public class TestContactInfo {
    private ContactInfo contactInfo;

    @Before
    public void setup() throws ParseException {
        Date birthdate = new SimpleDateFormat("dd/MM/yyyy").parse("12/06/2002");
        contactInfo = new ContactInfo("mo.a.alawneh@gmail.com","0592838433", birthdate,"Computer Engineering");
    }

    @Test
    public void testEmail() {
        contactInfo.setEmail("s12028067@stu.najah.edu");
        assertEquals("s12028067@stu.najah.edu",contactInfo.getEmail());
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
