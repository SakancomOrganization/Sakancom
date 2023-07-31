package enums;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class TestInfoStatus {
    @Test
    public void testDirty() {
        assertNotNull(InfoStatus.DIRTY);
    }

    @Test
    public void testRigected() {
        assertNotNull(InfoStatus.REJECTED);
    }

    @Test
    public void testAccepted() {
        assertNotNull(InfoStatus.ACCEPTED);
    }
}
