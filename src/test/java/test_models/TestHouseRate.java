package test_models;

import enums.UserType;
import exceptions.UnacceptableValueException;
import models.HouseRate;
import models.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestHouseRate {
    private HouseRate houseRate;
    private User firstUser;
    private User secondUser;

    @Before
    public void setup() {
        houseRate = new HouseRate();
        firstUser = new User("rater1", "PASS1", UserType.TENANT, null, null, null);
        secondUser = new User("rater2", "PASS2", UserType.TENANT, null, null,null);
    }

    @Test
    public void testSetRateAndGetRate() throws UnacceptableValueException {
        houseRate.setUserRate(firstUser, 4);
        houseRate.setUserRate(secondUser, 2);
        assertEquals(3.0, houseRate.getTotalRate(), 0.0);
        houseRate.setUserRate(firstUser, 3);
        assertEquals(2.5, houseRate.getTotalRate(), 0.0);
        houseRate.setUserRate(secondUser, 5);
        assertEquals(4.0, houseRate.getTotalRate(), 0.0);
    }
}
