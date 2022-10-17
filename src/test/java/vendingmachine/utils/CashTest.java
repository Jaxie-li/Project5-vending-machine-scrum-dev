package vendingmachine.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @version v1.0
 * @author: Louis Gan
 * @date: Created in 17/10/2022 9:08 pm
 * @description: This is the testcase for Cash
 */

public class CashTest {
    private Cash cash;

//    @BeforeEach

    @Test
    public void constructorTest(){
        assertNotNull(this.cash);
    }

    @Test
    public void getQualityTest(){
        assertEquals("quality",this.cash.getQuantity());
    }

}
