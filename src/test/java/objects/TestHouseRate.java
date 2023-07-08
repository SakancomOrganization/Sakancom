package objects;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestHouseRate {
    private HouseRate houseRate;

    @Before
    public void setup() {
        houseRate = new HouseRate();
    }

    @Test
    public void testAddNewRate() {
        // set the raters
        houseRate.setRaters(4);
        // set the rate
        houseRate.setRate(4.5);
        // add a new rater
        houseRate.addNewRate(3);
        // test the rate value
        assertEquals(4.2, houseRate.getRate(),0.0);
        // test the number of raters
        assertEquals(5, houseRate.getRaters());
    }

}