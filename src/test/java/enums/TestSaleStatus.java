package enums;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class TestSaleStatus {
    @Test
    public void testAvailable() {
        assertNotNull(SaleStatus.AVAILABLE);
    }

    @Test
    public void testUnavailable() {
        assertNotNull(SaleStatus.UNAVAILABLE);
    }
}
