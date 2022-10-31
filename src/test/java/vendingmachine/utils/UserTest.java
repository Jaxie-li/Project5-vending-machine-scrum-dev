package vendingmachine.utils;

import exceptions.UserNameExistException;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User owner;
    private User customer;
    private User seller;
    private User cashier;
    private User realCustomer;

    private String path = "src/test/resources/vendingmachine/data/userTest.json";
    private JSONArray data;


    @BeforeEach
    public void init() throws IOException, ParseException {
        // TODO: you might need to save these information into userTest.json database
        this.owner = new User("Katherine", "katherine", "owner");
        this.customer = new User("Jaxie", "jaxie", "customer");
        this.seller = new User("Leo", "leo", "seller");
        this.cashier = new User("Louis", "louis", "cashier");
        this.realCustomer = new User("testcustomer1", "123456", "customer");
        // Get User info from database
        this.data = (JSONArray) new JSONParser().parse(new FileReader(path));
    }

    @Test
    public void uniqueUsernameTest() {
        assertDoesNotThrow(()->User.setData(data));

        assertThrows(RuntimeException.class,()-> User.register("testcustomer1", "123456"));
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
//        // Only owner could create customer, so when owner creates customer it will not be null
//        assertNotNull(User.isValidUser("Yitong", "yitong"));
//
//        // When customer/ seller creates customer they will be null
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
        // TODO: shouldn't have this because cannot register the same username, when customer is already in database

        // assertNotNull(User.register("Jaxie","jaxie"));
        // assertEquals(customer.toString(), User.register("Jaxie","jaxie").toString());
    }

    @Test
    public void isValidUserTest() {
        // Fetch User information from database
        User.setData(this.data);

        // Should be null if the customer is not valid
        assertNull(User.isValidUser("AAA", "aaaaa"));

        // Should not be null if the customer exists. Test for customer, owner & seller
        assertNotNull(User.isValidUser("testcustomer1", "123456"));
        assertNotNull(User.isValidUser("testadmin1", "123456"));
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