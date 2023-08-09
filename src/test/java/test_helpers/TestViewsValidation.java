package test_helpers;

import org.junit.jupiter.api.Test;

import static helpers.ViewsValidation.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestViewsValidation {
    @Test
    void testIsValidUserField() {
        assertTrue(isValidUserField("firstname"));
        assertTrue(isValidUserField("SecondNamE"));
        assertTrue(isValidUserField("LASTName"));
        assertTrue(isValidUserField("city"));
        assertTrue(isValidUserField("STREET"));
        assertTrue(isValidUserField("building"));
        assertTrue(isValidUserField("floor"));
        assertTrue(isValidUserField("EmAiL"));
        assertTrue(isValidUserField("phoneNumber"));
        assertTrue(isValidUserField("birthdate"));
        assertTrue(isValidUserField("Major"));
        assertFalse(isValidUserField("date")); // negative testcase
        assertFalse(isValidUserField("name")); // negative testcase
        assertFalse(isValidUserField("name")); // negative testcase
    }

    @Test
    void testIsValidBuildingField() {
        assertTrue(isValidBuildingField("NaMe"));
        assertTrue(isValidBuildingField("City"));
        assertTrue(isValidBuildingField("streeT"));
        assertFalse(isValidBuildingField("id")); // negative testcase
        assertFalse(isValidBuildingField("owner")); // negative testcase
    }

    @Test
    void testIsValidHouseField() {
        assertTrue(isValidHouseField("monthlyrent"));
        assertTrue(isValidHouseField("includeselectricity"));
        assertTrue(isValidHouseField("includeswater"));
        assertTrue(isValidHouseField("Hasinternet"));
        assertTrue(isValidHouseField("hasBalcony"));
        assertTrue(isValidHouseField("bedroomsnum"));
        assertTrue(isValidHouseField("bathroomsnum"));
        assertTrue(isValidHouseField("houseClassificationByGender"));
        assertFalse(isValidHouseField("rent"));  // negative testcase
        assertFalse(isValidHouseField("balcony")); // negative testcase
        assertFalse(isValidHouseField("withWater")); // negative testcase
    }

    @Test
    void testIsNegativeNumber() {
        assertTrue(isNegativeNumber(-1));
        assertFalse(isNegativeNumber(0)); // negative testcase
        assertFalse(isNegativeNumber(1)); // negative testcase
    }
}
