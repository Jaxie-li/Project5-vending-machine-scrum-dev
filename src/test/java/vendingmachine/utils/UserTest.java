package vendingmachine.utils;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User owner;
    private User customer;
    private User seller;
    private User cashier;

    private User realCustomer;


    @BeforeEach
    public void init() {
        this.owner = new User("Katherine", "katherine", "owner");
        this.customer = new User("Jaxie", "jaxie", "customer");
        this.cashier = new User("Louis", "louis", "cashier");
        this.seller = new User("Leo", "leo", "seller");
        this.realCustomer = new User("testcustomer1", "123456", "customer");
    }

//    @Test
//    void createUserTest() {
//        // Fetch User information from database
//        User.setData(User.read(User.path));
//
//        // Creates users
//        owner.createUser("Yitong", "yitong", "customer");
//        customer.createUser("Joyce", "joyce", "customer");
//        seller.createUser("AAAA", "aaaa", "owner");
//
//        // Only owner could create user, so when owner creates user it will not be null
//        assertNotNull(User.isValidUser("Yitong", "yitong"));
//
//        // When customer/ seller creates user they will be null
//        assertNull(User.isValidUser("Joyce", "joyce"));
//        assertNull(User.isValidUser("AAAA", "aaaa"));
//    }

    @Test
    public void serialiseTest() {
        assertNotNull(owner.serialise());
        assertNotNull(customer.serialise());
        assertNotNull(seller.serialise());
        assertNotNull(cashier.serialise());
    }

//    @Test
//    void deleteUser() {
//    }

    @Test
    public void registerTest() {
        User.setData(User.read(User.path));
        assertNotNull(User.register("Jaxie","jaxie"));
        // TODO: shouldn't have this because cannot register the same username, when customer is already in database
//        assertEquals(customer.toString(), User.register("Jaxie","jaxie").toString());
    }

    @Test
    public void isValidUserTest() {
        // Fetch User information from database
        User.setData(User.read(User.path));

        // Should be null if the user is not valid
        assertNull(User.isValidUser("AAA", "aaaaa"));

        // Should not be null if the user exists. Test for customer, owner & seller
        assertNotNull(User.isValidUser("testcustomer1", "123456"));
        assertNotNull(User.isValidUser("testowner1", "123456"));
        assertNotNull(User.isValidUser("testseller1", "123456"));
    }

    @Test
    public void getUsernameTest() {
        assertEquals("Katherine", owner.getUsername());
        assertEquals("Jaxie", customer.getUsername());
        assertEquals("Louis", cashier.getUsername());
        assertEquals("Leo", seller.getUsername());
    }

    @Test
    public void getUserTypeTest() {
        assertEquals("owner", owner.getUserType());
        assertEquals("customer", customer.getUserType());
        assertEquals("seller", seller.getUserType());
        assertEquals("cashier", cashier.getUserType());
    }

    @Test
    public void getSavedCard() {
        // Fetch User information from database
        User.setData(User.read(User.path));
        assertNull(owner.getSavedCard());
        assertNull(customer.getSavedCard());
        assertNull(seller.getSavedCard());
        assertNull(cashier.getSavedCard());
        // real customer in database have a card
        // assert not null
    }

    @Test
    public void setSavedCard() {
        Card testCardAdmin = new Card("CBA", "5102000000000000");
        Card testCardCustomer = new Card("CBA", "5102111111111111");
        owner.setSavedCard(testCardAdmin);
        customer.setSavedCard(testCardCustomer);
        assertEquals(testCardAdmin, owner.getSavedCard());
        assertNotEquals(testCardCustomer, owner.getSavedCard());
        assertEquals(testCardCustomer, customer.getSavedCard());
    }
}