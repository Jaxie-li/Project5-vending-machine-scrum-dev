package vendingmachine.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CancelledOrderTest {

    private CancelledOrder cancelledOrderTest;

    @BeforeEach
    public void init() {
        cancelledOrderTest = new CancelledOrder();
    }

    @Test
    public void getData() {
        assertNotNull(CancelledOrder.getData());
    }

    @Test
    public void constructorTest() {
        assertNotNull(cancelledOrderTest.serialise());
    }

    @Test
    public void getUsernameTest() {
        assertNull(cancelledOrderTest.getUsername());
        cancelledOrderTest.setUsername("Joyce");
        assertEquals("Joyce", cancelledOrderTest.getUsername());
    }

    @Test
    public void getReasonTest() {
        assertNull(cancelledOrderTest.getReason());
        cancelledOrderTest.setReason("user cancelled");
        assertEquals("user cancelled", cancelledOrderTest.getReason());
    }

    @Test
    public void getIdTest() {
        // id was not null, but 0 after initialising but not parsing anything
        assertNotNull(cancelledOrderTest.getId());
        cancelledOrderTest.setId(1);
        assertNotNull( cancelledOrderTest.getId());
        assertEquals(1, cancelledOrderTest.getId());
        assertNotEquals(2, cancelledOrderTest.getId());
    }

    @Test
    public void getNextIdTest() {
        int size = CancelledOrder.getData().size();
        assertEquals(size + 1, cancelledOrderTest.getNextId());
        assertNotEquals(size, cancelledOrderTest.getNextId());
    }


    @Test
    public void addCancelOrderTest() {
        cancelledOrderTest.addCancelOrder();
        assertNotNull(CancelledOrder.getData().size());
    }
}