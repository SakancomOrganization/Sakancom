package enums;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class TestHouseClassificationByGender {
    @Test
    public void testMale() {
        assertNotNull(HouseClassificationByGender.MALE);
    }

    @Test
    public void testFemale() {
        assertNotNull(HouseClassificationByGender.FEMALE);
    }

    @Test
    public void testFamily() {
        assertNotNull(HouseClassificationByGender.FAMILY);
    }
}
