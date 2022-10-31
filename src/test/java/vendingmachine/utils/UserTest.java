package vendingmachine.utils;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User admin;
    private User customer;
    private User seller;
    private User cashier;

    private User realCustomer;


    @BeforeEach
    public void init() throws IOException, ParseException {
        this.admin = new User("Katherine", "katherine", "owner");
        this.customer = new User("Jaxie", "jaxie", "customer");
        this.cashier = new User("Louis", "louis", "cashier");
        this.seller = new User("Leo", "leo", "seller");
        this.realCustomer = new User("testcustomer1", "123456", "customer");
    }

//    @Test
//    void createUserTest() {
//        // Fetch User information from database
//        User.setData(User.read(User.path));
//        // Only owner could create user?
//        admin.createUser("Yitong", "yitong", "customer");
////        customer.createUser("Joyce", "joyce", "customer");
////        seller.createUser("AAAA", "aaaa", "owner");
//        assertNotNull(User.isValidUser("Yitong", "yitong"));
////        assertNull(User.isValidUser("Joyce", "joyce"));
////        assertNull(User.isValidUser("AAAA", "aaaa"));
//    }

    @Test
    public void serialiseTest() {
        assertNotNull(admin.serialise());
        assertNotNull(customer.serialise());
        assertNotNull(seller.serialise());
        assertNotNull(cashier.serialise());
    }

    @Test
    void deleteUser() {
    }

//    @Test
//    void registerThrowsUserNameExistException() {
//        assertThrows(UserNameExistException, new Executable() {
//            @Override
//            public void execute() throws Throwable {
//                User.register("testcustomer1","123456");
//            }
//        });
//    }

//    @Test
//    public void register() {
//        User.setData(User.read(User.path));
//        assertNotNull(User.register("Jaxie","jaxie"));
//        assertEquals(customer.toString(), User.register("Jaxie","jaxie").toString());
//    }

//    @Test
//    public void isValidUserTest() {
//        // Fetch User information from database
//        User.setData(User.read(User.path));
////        User tempUser = new User("AAA", "aaaaa", "customer");
////        tempUser.serialise();
//        assertNull(User.isValidUser("AAA", "aaaaa"));
//        assertNotNull(User.isValidUser("testcustomer1", "123456"));
//        // expected: <User{userType='customer', savedCard=null}> but was: <User{userType='customer', savedCard=vendingmachine.utils.Card@2ca65ce4}>
//        //assertEquals(realCustomer, User.isValidUser("testcustomer1", "123456"));
//    }

    @Test
    public void getUsernameTest() {
        assertEquals("Katherine", admin.getUsername());
        assertEquals("Jaxie", customer.getUsername());
        assertEquals("Louis", cashier.getUsername());
        assertEquals("Leo", seller.getUsername());
    }

    @Test
    public void getUserTypeTest() {
        assertEquals("owner", admin.getUserType());
        assertEquals("customer", customer.getUserType());
        assertEquals("seller", seller.getUserType());
        assertEquals("cashier", cashier.getUserType());
    }

    @Test
    public void getSavedCard() {
        // Fetch User information from database
        User.setData(User.read(User.path));
        assertNull(admin.getSavedCard());
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
        admin.setSavedCard(testCardAdmin);
        customer.setSavedCard(testCardCustomer);
        assertEquals(testCardAdmin, admin.getSavedCard());
        assertNotEquals(testCardCustomer, admin.getSavedCard());
        assertEquals(testCardCustomer, customer.getSavedCard());
    }
}