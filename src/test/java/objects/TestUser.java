package objects;

import enums.UserType;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestUser {
    private User user;
    private Furniture furniture;

    @Before
    public void setup() throws ParseException {
        furniture = new Furniture("A new furniture description", "A new furniture image");
        user = new User("mo-alawneh",
                "Mohammad12002",
                UserType.ADMIN,
                new Name("Mohammad","AbdAllateef","Alawneh"),
                new UserLocation("Jenin","Abu-Baker Street","4070",1),
                new ContactInfo("mo.a.alawneh@gmail.com","0592838433",new SimpleDateFormat("dd/MM/yyyy").parse("12/06/2002"),"Computer Engineering"));
    }

    @Test
    public void testUsername() {
        user.setUsername("najat-mansour");
        assertEquals("najat-mansour", user.getUsername());
    }

    @Test
    public void testPassword() {
        user.setPassword("Najat123456");
        assertEquals("Najat123456", user.getPassword());
    }

    @Test
    public void testUserType() {
        user.setUserType(UserType.OWNER);
        assertEquals(UserType.OWNER, user.getUserType());
    }

    @Test
    public void testName() {
        Name newName = new Name("Najat","Sameer","Mansour");
        user.setName(newName);
        assertEquals(user.getName(), newName);
    }

    @Test
    public void testUserLocation() {
        UserLocation userLocation = new UserLocation("Jenin","Abu-Baker","Personal Building",1);
        user.setUserLocation(userLocation);
        assertEquals(userLocation, user.getUserLocation());
    }

    @Test
    public void testContactInfo() throws ParseException {
        ContactInfo contactInfo = new ContactInfo("mo.a.alawneh@gmail.com",
                "0592838433",
                new SimpleDateFormat("dd/MM/yyyy").parse("12/06/2002"),
                "Computer Engineering");
        user.setContactInfo(contactInfo);
        assertEquals(user.getContactInfo(), contactInfo);
    }

    @Test
    public void testFurniture() {
        List<Furniture> furnitureList = new ArrayList<>();
        furnitureList.add(furniture);
        user.setFurnitureList(furnitureList);
        assertEquals(user.getFurnitureList(), furnitureList);
    }

    @Test
    public void testAddFurniture() {
        // test adding a new furniture
        user.addFurniture(furniture);
        assertTrue(user.getFurnitureList().contains(furniture));

        // test adding an existing furniture (the size must remain the same)
        int furnitureListSize = user.getFurnitureList().size();
        user.addFurniture(furniture);
        assertEquals(furnitureListSize, user.getFurnitureList().size());
    }

    @Test
    public void testRemoveFurniture() {
        user.addFurniture(furniture);
        user.removeFurniture(furniture);
        assertFalse(user.getFurnitureList().contains(furniture));
    }

    @Test
    public void testEquals() {
        // object from another type
        assertNotEquals(user, new Object());
        // equal
        assertEquals(user, new User("mo-alawneh",
                "",
                UserType.ADMIN,
                null,
                null,
                null));
        // unequal
        assertNotEquals(user, new User("alawneh-mo",
                "",
                UserType.ADMIN,
                null,
                null,
                null));
    }

    @Test
    public void testHashCode() throws ParseException {
        User anotherUser = new User("mo-alawneh",
                "Mohammad12002",
                UserType.ADMIN,
                new Name("Mohammad","AbdAllateef","Alawneh"),
                new UserLocation("Jenin","Abu-Baker Street","4070",1),
                new ContactInfo("mo.a.alawneh@gmail.com","0592838433",new SimpleDateFormat("dd/MM/yyyy").parse("12/06/2002"),"Computer Engineering"));
        assertEquals(user.hashCode(), anotherUser.hashCode());
    }
}
