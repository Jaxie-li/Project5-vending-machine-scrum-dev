package vendingmachine.utils;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private Order order;

    private static final String path = "src/test/resources/orderHistoryTest.json";
    private User user;

    private final ArrayList<Product> products = new ArrayList<>();


    @BeforeEach
    public void init() {
        this.user = new User("Tester", "123", "owner");
        this.order = new Order(user);
    }

//    @Test
//    void getData() {
//    }
//
//    @Test
//    void setData() {
//    }
//
//    @Test
//    void addProduct() {
//
//    }
//
//    @Test
//    void payByCash() {
//    }
//
//    @Test
//    void payByCard() {
//    }
//
//    @Test
//    void getOrderTotal() {
//
//    }
//
//    @Test
//    void cancelByTimeOut() {
//    }
//
//    @Test
//    void cancelByUser() {
//    }
//
//    @Test
//    void cancelByNoExchange() {
//    }
//
//    @Test
//    void finalizeOrder() {
//    }
//
//    @Test
//    void serialize() {
//    }
}