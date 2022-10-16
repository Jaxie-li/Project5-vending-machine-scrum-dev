package vendingmachine.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @version v1.0
 * @author: Katherine Xu
 * @date: Created in 16/10/2022 11:27 pm
 * @description: This is the testcase for Card
 */

public class CardTest {
    private Card card;

    @BeforeEach
    public void init(){
        this.card = new Card("admin","123456");
    }

    @Test
    public void constructorTest(){
        assertNotNull(this.card);
    }

    @Test
    public void getNameTest(){
        assertEquals("admin",this.card.getName());
        assertNotEquals("root",this.card.getName());
    }

}
