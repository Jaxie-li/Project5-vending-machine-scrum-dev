package vendingmachine.utils;

import exceptions.UserNameExistException;
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
    private User user;
    private User seller;
    private User cashier;

    private String path = "src/test/resources/userTest.json";
    private JSONArray data;


    @BeforeEach
    public void init() throws IOException, ParseException {
        // TODO: you might need to save these information into userTest.json database
        this.admin = new User("Katherine", "katherine", "owner");
        this.user = new User("Jaxie", "jaxie", "customer");
        this.seller = new User("Leo", "leo", "seller");
        this.cashier = new User("Louis", "louis", "cashier");
        // Get User info from database
        this.data = (JSONArray) new JSONParser().parse(new FileReader(path));
    }
    
    @Test
    public void uniqueUsernameTest() {
        assertDoesNotThrow(()->User.setData(data));

        assertThrows(RuntimeException.class,()-> User.register("testcustomer1", "123456"));
    }

    @Test
    void createUser() {

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
//    void register() {
//        User.setData(User.read(User.path));
//        assertNull(User.register("Jaxie","jaxie"));
//        assertEquals(user.toString(), User.register("Jaxie","jaxie").toString());
//    }

    @Test
    void isValidUser() {
    }

    @Test
    void getUsername() {
    }

    @Test
    void getUserType() {
    }

    @Test
    void getSavedCard() {
    }

    @Test
    void setSavedCard() {
    }
}