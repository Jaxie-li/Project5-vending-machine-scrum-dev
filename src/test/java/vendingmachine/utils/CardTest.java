package vendingmachine.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

    @Test
    public void checkCreditCardValidTest(){
        assertFalse(Card.checkCreditCardValid("admin","admin"));
        assertFalse(Card.checkCreditCardValid("Charles","asdjhf"));
        assertTrue(Card.checkCreditCardValid("Charles","40691"));
    }

    @Test
    public void checkCreditCardValidTwoTest(){
        assertFalse(Card.checkCreditCardValid("admin","asdhgsdj",new User("admin","admin","customer"),true));
        assertFalse(Card.checkCreditCardValid("Charles","asdhgsdj",new User("Charles","admin","customer"),true));
        assertTrue(Card.checkCreditCardValid("Charles","40691",new User("Charles","40691","customer"),true));
    }

    @Test
    public void serialiseTest(){
        assertNotNull(card.serialise());
    }

    @Test
    public void getNumberTest(){
        assertEquals("123456",card.getNumber());
    }


}
