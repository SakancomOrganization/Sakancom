package enums;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class TestUserType {
    @Test
    public void testAdmin() {
        assertNotNull(UserType.ADMIN);
    }

    @Test
    public void testOwner() {
        assertNotNull(UserType.OWNER);
    }

    @Test
    public void testTenant() {
        assertNotNull(UserType.TENANT);
    }
}
