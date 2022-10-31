package vendingmachine.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CancelledOrderTest {

    private CancelledOrder cancelledOrderTest;

    @BeforeEach
    public void init() {
        this.cancelledOrderTest = new CancelledOrder();
    }

    @Test
    public void getData() {
        assertNotNull(CancelledOrder.getData());
    }

    @Test
    public void constructorTest() {
        assertNotNull(this.cancelledOrderTest.serialise());
    }

    @Test
    public void getUsernameTest() {
        assertNull(this.cancelledOrderTest.getUsername());
        this.cancelledOrderTest.setUsername("Joyce");
        assertEquals("Joyce", this.cancelledOrderTest.getUsername());
    }

    @Test
    public void getReasonTest() {
        assertNull(this.cancelledOrderTest.getReason());
        this.cancelledOrderTest.setReason("user cancelled");
        assertEquals("user cancelled", this.cancelledOrderTest.getReason());
    }

    @Test
    public void getIdTest() {
        // id was not null, but 0 after initialising but not parsing anything
        assertNotNull(this.cancelledOrderTest.getId());
        this.cancelledOrderTest.setId(1);
        assertNotNull( this.cancelledOrderTest.getId());
        assertEquals(1, this.cancelledOrderTest.getId());
        assertNotEquals(2, this.cancelledOrderTest.getId());
    }

    @Test
    public void getNextIdTest() {
        int size = CancelledOrder.getData().size();
        assertEquals(size + 1, this.cancelledOrderTest.getNextId());
        assertNotEquals(size, this.cancelledOrderTest.getNextId());
    }


    @Test
    public void addCancelOrderTest() {
        this.cancelledOrderTest.addCancelOrder();
        assertNotNull(CancelledOrder.getData().size());
    }
}